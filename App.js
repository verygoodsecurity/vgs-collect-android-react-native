import React, { Component } from 'react';
import {
  StyleSheet,
  View,
  Text,
  NativeEventEmitter,
  Button,
} from 'react-native';

import {
  Colors,
} from 'react-native/Libraries/NewAppScreen';

import CardNumberLayout from './fields/number/NativeView';
import NumberVGSEditText from './fields/number/EditText';

import CardCVCLayout from './fields/cvc/NativeView';
import CVCVGSEditText from './fields/cvc/EditText';

import CardHolderLayout from './fields/holder/NativeView';
import HolderVGSEditText from './fields/holder/EditText';

import VGSCollect from './VGSCollect';
import ScanActivity from './ScanActivity';

import { DeviceEventEmitter } from 'react-native';


type Props = {}
export default class App extends Component<Props> {
constructor(props) {
    super(props);
    this.listener = DeviceEventEmitter.addListener('onVGSResponse', e => this.showUserData(e));
    this.listener = DeviceEventEmitter.addListener('onVGSStateChange', e => this.showUserData(e));
    this.state = {
      titleText: "Status:\n",
      bodyText: "",
    };
  }

     showUserData = (msg) => {
         this.setState({ bodyText: msg })
     }


     handleClick = () => {
         NumberVGSEditText.getFieldName(
             (msg) => {
                ScanActivity.setItem(msg, ScanActivity.CARD_NUMBER);
             }
         );
         HolderVGSEditText.getFieldName(
             (msg) => {
                ScanActivity.setItem(msg, ScanActivity.CARD_HOLDER);
             }
         );

         setTimeout(function(){
              ScanActivity.startActivityForResult();
         }, 200);
     }

    render() {

        return (
        <>
            <View style={{flex: 1,flexDirection: 'row'}}>
                  <View style={{
                    flex: 3,
                    justifyContent: 'flex-start',
                    flexDirection: 'column',
                    alignItems: 'stretch',
                    padding:16,
                  }}>

                <View style={{height: 60 }} >
                    <CardNumberLayout style={{height: 50, backgroundColor: 'powderblue'}} />
                </View>
                <View style={{height: 60 }} >
                    <CardHolderLayout style={{height: 50, backgroundColor: 'skyblue'}} />
                </View>
                <View style={{height: 60 }} >
                    <CardCVCLayout style={{height: 50, backgroundColor: 'skyblue'}} />
                </View>

                <View style={{height: 60 }} >
                    <Button
                        style={styles.button}
                        title="Submit"
                        onPress={() => VGSCollect.submitAsync()}
                    />
                </View>

                <View style={{height: 60 }} >
                    <Button
                        style={styles.button}
                        title="Scan"
                        onPress={this.handleClick}
                    />
                </View>

              </View>
              <View style={{
                  flex: 2,
                  flexDirection: 'column',
                  marginTop:25,
                }}>
                    <Text style={{fontWeight: 'bold'}}>
                           {this.state.titleText}
                     </Text>
                    <Text style={styles.titleText}
                        onPress={this.onPressTitle}
                        onChangeText = {this.showUserData}>
                       {this.state.bodyText}
                     </Text>
                </View>
            </View>
        </>
        );
    }
};

const styles = StyleSheet.create({
  body: {
    padding:16,
    flex:1,
    flexDirection: 'row',
        flexDirection: 'column',
        alignItems: 'stretch',
  },

  field: {
    height: 10,
  },
  button: {
    marginTop:50,
    marginBottom:50,
    backgroundColor:'skyblue'
  },

});
