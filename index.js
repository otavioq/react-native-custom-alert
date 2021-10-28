/**
 * @providesModule RNCustomAlert
 * @author Doko
 * @flow
 */

'use strict';

import React, { NativeModules, Platform } from 'react-native';

const Native = Platform.OS === 'android' ? NativeModules.RNCustomAlert : NativeModules.SweetAlertManager;

const DEFAULT_OPTIONS = {
  title: '',
  subTitle: '',
  confirmButtonTitle: 'OK',
  confirmButtonColor: '#000000',
  barColor: '',
  otherButtonTitle: 'Cancel',
  otherButtonColor: '#dedede',
  style: 'success',
  cancellable: true
}

const CustomAlert = {
  showAlertWithOptions: (options, callback = () => {}) => {
    Native.showAlertWithOptions(options ? options : DEFAULT_OPTIONS, callback)
  },
  dismissAlert: () => Native.hideSweetAlert(),
};

export default CustomAlert;
