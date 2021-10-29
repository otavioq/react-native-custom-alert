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
  confirmButtonTitle: 'Ok',
  confirmButtonColor: '#000000',
  barColor: '',
  otherButtonTitle: 'Cancelar',
  otherButtonColor: '#dedede',
  style: 'success',
  cancellable: true
}

export default CustomAlert = {
  showAlertWithOptions: (options, callback = () => {}) => {
    Native.showAlertWithOptions(options ? options : DEFAULT_OPTIONS, callback)

    if(options.delay && !isNaN(options.delay) && parseInt(options.delay) > 0){

      setTimeout( Native.hideSweetAlert, parseInt(options.delay) )
    }
  },
  dismissAlert: () => Native.hideSweetAlert(),
};
