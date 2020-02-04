import React from 'react';
import { requireNativeComponent, View } from 'react-native';
import PropTypes from 'prop-types';

const propTypes = {
    url: PropTypes.string,
    ...View.propTypes,
};

const NativeView = ({ someRandomProp }) => (
    <CardCVCLayout
        style={{ flex: 1 }}
        someRandomProp={someRandomProp}
    />
);

NativeView.propTypes = propTypes;
const CardCVCLayout = requireNativeComponent('CardCVCLayout', NativeView);

export default NativeView;
