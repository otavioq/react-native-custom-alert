'use strict';

import { NativeModules, Platform } from 'react-native';

const Native = Platform.OS === 'android' ? NativeModules.RNCustomAlert : NativeModules.SweetAlertManager;

const DEFAULT_OPTIONS = {
  title: '',
  subTitle: '',
  confirmButtonTitle: null,
  confirmColor: '#000000',
  barColor: '',
  otherButtonTitle: null,
  cancelColor: '#dedede',
  style: 'success',
  cancellable: true,
  delay: null
}

const CustomAlert = {
  showAlertWithOptions: (options, callback = () => {}) => {
    Native.showAlertWithOptions(options ? options : DEFAULT_OPTIONS, callback)

    if(options.delay && !isNaN(options.delay) && parseInt(options.delay) > 0){

      setTimeout( Native.hideSweetAlert, parseInt(options.delay) )
    }
  },
  dismissAlert: () => Native.hideSweetAlert(),
};

export default CustomAlert