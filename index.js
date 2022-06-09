'use strict';

import { NativeModules, Platform } from 'react-native';

const android = Platform.OS === 'android'

const Native = android ? NativeModules.RNCustomAlert : NativeModules.SweetAlertManager;

const DEFAULT_OPTIONS = {
  title: '',
  subTitle: '',
  confirmButtonTitle: 'Ok',
  confirmButtonColor: '#27ae60',
  barColor: '',
  otherButtonTitle: 'Cancel',
  otherButtonColor: '#d63031',
  style: 'success',
  showCancel: false,
  cancelable: false,
  delay: 0
}

const CustomAlert = {
  /**
   * @param {DEFAULT_OPTIONS} options
   * @param {*} callback
   */
  showAlertWithOptions: (options, callback = () => {}) => {
    Native.showAlertWithOptions(options ? options : DEFAULT_OPTIONS, callback)

    if(options.delay && !isNaN(options.delay) && parseInt(options.delay) > 0){

      setTimeout( Native.hideSweetAlert, parseInt(options.delay) )
    }
  },
  /**
   * Dismisses the alert
   */
  dismissAlert: () => {Native.hideSweetAlert()}
};

export default CustomAlert