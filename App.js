import React, { Component } from 'react';
import {StyleSheet, View, Text, NativeEventEmitter, Button } from 'react-native';

import {
  Colors,
} from 'react-native/Libraries/NewAppScreen';

import CardNumberLayout from './fields/number/NativeView';
import NumberVGSEditText from './fields/number/EditText';

import CardExpDateLayout from './fields/date/NativeView';
import ExpDateVGSEditText from './fields/date/EditText';

import VGSCollect from './VGSCollect';
import ScanActivity from './ScanActivity';

import { DeviceEventEmitter } from 'react-native';

export default class App extends  Component<Props> {
    constructor(props) {
        super(props);
        this.listener = DeviceEventEmitter.addListener('onVGSResponse', e => this.showUserData(e));
        this.listener = DeviceEventEmitter.addListener('cardNumberToken', e => this.showToken1(e));
        this.listener = DeviceEventEmitter.addListener('expirationDateToken', e => this.showToken2(e));
        this.state = {
          bodyText: "Code:",
          token1: " ",
          token2: " ",
        };
    }

    showUserData = (msg) => {
        this.setState({ bodyText: msg })
    }

    showToken1 = (msg) => {
        this.setState({ token1: msg })
    }

    showToken2 = (msg) => {
        this.setState({ token2: msg })
    }


    handleClick = () => {
        NumberVGSEditText.getFieldName((msg) => {
            ScanActivity.setItem(msg, ScanActivity.CARD_NUMBER);
        });

        setTimeout( function() {
            ScanActivity.startActivityForResult();
        }, 200);
    }

    render() {
        return (
            <View style={{
                flex: 1,
                flexDirection: 'row',
                justifyContent:'center'
            }}>
                <View style={{
                    width:'49%', height: '100%', padding:3,
                }}>
                    <CardNumberLayout
                        style={styles.collectField}
                        hint={'Card Number'}
                        corners={12}
                        fontSize={10}
                        padding={3}
                    />
                    <Text
                        style={styles.tokenInfo}
                        numberOfLines={1}
                        onChangeText = {this.showToken1}>
                            {this.state.token1}
                    </Text>
                    <CardExpDateLayout
                        style={styles.collectField}
                        hint={'Expiration Date'}
                        corners={12}
                        fontSize={10}
                        padding={3}
                     />
                     <Text
                         style={styles.tokenInfo}
                         numberOfLines={1}
                         onChangeText = {this.showToken1}>
                         {this.state.token2}
                     </Text>

                    <View style={{
                        marginBottom:20, marginLeft:20, marginRight:20
                    }}>
                        <Button style={styles.button}
                            title="Submit"
                            onPress={ () => VGSCollect.submitAsync() }
                        />
                    </View>

                    <View style={{
                        marginBottom:20, marginLeft:20, marginRight:20
                    }}>
                        <Button style={styles.button}
                            title="Scan"
                            onPress={this.handleClick}
                        />
                    </View>

                    <View style={styles.bodyResponse }>
                        <Text style={styles.titleText}
                            onPress={this.onPressTitle}
                            onChangeText = {this.showUserData}
                            >
                            {this.state.bodyText}
                        </Text>
                    </View>
                </View>


                <View style={{
                        width: 1, height: '100%', backgroundColor: 'black'
                      }} />


                <View style={{ padding:3, width:'49%', height: '100%' }}>

                    <Text
                        style={styles.showField}
                        numberOfLines={1}
                        >
                        Card Number
                    </Text>

                    <Text style={styles.showField} numberOfLines={1}>
                        Expiration Date
                    </Text>

                    <View style={{
                        marginBottom:20, marginLeft:20, marginRight:20
                    }}>
                        <Button style={styles.button}
                            title="Scan"
                            onPress={this.handleClick}
                        />
                    </View>

                </View>
            </View>
    );}}

var styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  tokenInfo: {
    fontSize: 9,
    marginBottom: 16
  },
  collectField: {
    width:'100%',
    height: 40,
    marginBottom: 4
  },
  showField: {
    width:'100%',
    height: 40
  },
  button: {
    padding: 14,
    backgroundColor:'skyblue'
  },
});

/*
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

         setTimeout(function(){
              ScanActivity.startActivityForResult();
         }, 200);
     }

    render() {

        return (
        <>
            <View style={{flex: 1,flexDirection: 'row'}}>
                <View style={styles.bodyContent }>
                    <View style={styles.container} >
                        <CardNumberLayout
                            style={styles.field}
                            hint={'Card Number'}
                            corners={15}
                            />
                    </View>
                    <View style={styles.container} >
                        <CardExpDateLayout style={styles.field}
                        />
                    </View>
                    <View style={styles.container} >
                        <Button style={styles.button}
                            title="Submit"
                            onPress={() => VGSCollect.submitAsync()}
                        />
                    </View>
                    <View style={styles.container} >
                        <Button style={styles.button}
                            title="Scan"
                            onPress={this.handleClick}
                        />
                    </View>

                </View>
                <View style={styles.bodyResponse }>
                    <Text style={styles.responseTitle}>
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
  bodyContent: {
      flex: 3,
      justifyContent: 'flex-start',
      flexDirection: 'column',
      alignItems: 'stretch',
      padding:16,
  },

  bodyResponse: {
      flex: 2,
      flexDirection: 'column',
      marginTop:25,
  },
  container: {
    height: 60,
  },

  field: {
    height: 50,
  },

  responseTitle: {
    fontWeight: 'bold',
  },
  button: {
    marginTop:50,
    marginBottom:50,
    backgroundColor:'skyblue'
  },

});
*/
