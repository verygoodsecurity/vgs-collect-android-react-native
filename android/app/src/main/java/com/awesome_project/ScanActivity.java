package com.awesome_project;

import android.content.Intent;
import android.os.Bundle;

import com.verygoodsecurity.vgscollect.app.BaseTransmitActivity;

import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class ScanActivity extends BaseTransmitActivity {
    public static final String SCAN_CONFIGURATION = "vgs_scan_settings";
    public static final int CARD_NUMBER = 0x71;
    public static final int  CARD_CVC = 0x72;
    public static final int  CARD_HOLDER = 0x73;
    public static final int  CARD_EXP_DATE = 0x74;
    public static final int  POSTAL_CODE = 0x75;

    private static final int  CARD_IO_REQUEST_CODE = 0x7;

    private Map<String, Integer> settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        saveSettings();

        runCardIO();
    }

    private void runCardIO() {
        Intent scanIntent = new Intent(this, CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true)
                .putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, true)
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true)
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, true)
                .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        startActivityForResult(scanIntent, CARD_IO_REQUEST_CODE);
    }

    private void saveSettings() {
        if(getIntent() != null && getIntent().getExtras() != null) {
            settings = (HashMap<String, Integer>)getIntent().getExtras().getSerializable(SCAN_CONFIGURATION);
        } else {
            settings = new HashMap();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CARD_IO_REQUEST_CODE) {
            if(data != null &&
                    data.getExtras() != null &&
                    data.getExtras().containsKey(CardIOActivity.EXTRA_SCAN_RESULT)
            ) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                for (Map.Entry<String,Integer> entry : settings.entrySet()) {
                    switch (entry.getValue()) {
                        case CARD_NUMBER:
                            mapData(entry.getKey(), scanResult.cardNumber);
                            break;
                        case CARD_CVC: mapData(entry.getKey(), scanResult.cvv);
                            break;
                        case CARD_HOLDER: mapData(entry.getKey(), scanResult.cardholderName);
                            break;
                        case CARD_EXP_DATE: if(scanResult.expiryMonth != 0 && scanResult.expiryYear != 0) {
                                mapData(entry.getKey(), String.format("%02d/%02d", scanResult.expiryMonth, scanResult.expiryYear));
                            }
                            break;
                        case POSTAL_CODE: mapData(entry.getKey(), scanResult.postalCode);
                            break;
                    }
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
