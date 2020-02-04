import React from 'react';
import { requireNativeComponent, View } from 'react-native';
import PropTypes from 'prop-types';

const propTypes = {
    url: PropTypes.string,
    ...View.propTypes,
};

const NativeView = ({ someRandomProp }) => (
    <CardNumberLayout
        style={{ flex: 1 }}
        someRandomProp={someRandomProp}
    />
);

NativeView.propTypes = propTypes;
const CardNumberLayout = requireNativeComponent('CardNumberLayout', NativeView);

export default NativeView;
