import React from 'react';
import { requireNativeComponent, View } from 'react-native';
import PropTypes from 'prop-types';

const propTypes = {
    url: PropTypes.string,
    ...View.propTypes,
};

const NativeView = ({ someRandomProp }) => (
    <VGSEditText
        style={{ flex: 1 }}
        someRandomProp={someRandomProp}
    />
);

NativeView.propTypes = propTypes;
const VGSEditText = requireNativeComponent('VGSEditText', NativeView);

export default NativeView;