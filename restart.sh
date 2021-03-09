#!/bin/bash

ASSETS_DIR="./android/app/src/main/assets/"

if [ -d $ASSETS_DIR ]
then
    echo "-----> Directory EXISTS"

    if [ "$(ls -A $ASSETS_DIR)" ]; then
        echo " is not Empty"
        rm -f $ASSETS_DIR*
        echo "======> Clearing..."
    fi

    echo "======> Start building"
    cd android
    ./gradlew clean
    cd ../
    npx react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res
    npx react-native run-android
    echo "======> DONE"
else
    echo "- ======> Directory NOT EXISTS"
    mkdir $ASSETS_DIR

    echo "======> Start building"
    cd android
    ./gradlew clean
    cd ../
    npx react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res
    npx react-native run-android
    echo "======> DONE"
fi