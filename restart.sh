#!/bin/bash

cd android
./gradlew clean
cd ../
npx react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res
npx react-native run-android