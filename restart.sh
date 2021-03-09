#!/bin/bash

ASSETS_DIR="./android/app/src/main/assets/"

if [ -d $ASSETS_DIR ]
then
    if [ "$(ls -A $ASSETS_DIR)" ]; then
        rm -f $ASSETS_DIR*
    fi

    cd android
    ./gradlew clean
    cd ../
    npx react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res
    npx react-native run-android
else
    mkdir $ASSETS_DIR

    cd android
    ./gradlew clean
    cd ../
    npx react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res
    npx react-native run-android
fi