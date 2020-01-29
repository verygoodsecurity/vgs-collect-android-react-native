import React, { Component } from 'react';
import {
  StyleSheet,
  View,
  Button,
} from 'react-native';

import {
  Colors,
} from 'react-native/Libraries/NewAppScreen';

import NativeView from './NativeView';
import VGSCollect from './VGSCollect';

type Props = {}
export default class App extends Component<Props> {

    render() {

        return (
        <>
            <View style={styles.body}>
                <NativeView style={styles.field} />
                <Button
                    style={styles.button}
                    title="Submit"
                    onPress={() => VGSCollect.submitAsync()}
                />
            </View>
        </>
        );
    }
};

const styles = StyleSheet.create({
  body: {
    height:150,
    padding:16,
  },

  field: {
    height:100,
  },
  button: {
    height:100,
    backgroundColor:'skyblue'
  },

});
