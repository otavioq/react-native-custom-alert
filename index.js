'use strict';

import { Alert, NativeModules, Platform } from 'react-native';

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
		Native.showAlertWithOptions(options ? options : DEFAULT_OPTIONS, (action) => {
			if (action == 'error') {
				defaultAlert(options, callback)
			} else {
				callback(action)
			}
		})

		if (options.delay && !isNaN(options.delay) && parseInt(options.delay) > 0) {

			setTimeout(Native.hideSweetAlert, parseInt(options.delay))
		}
	},
	/**
	 * Dismisses the alert
	 */
	dismissAlert: () => { Native.hideSweetAlert() }
};

const defaultAlert = (options, callback = () => {}) => {

	const buttons = []
	buttons.push({
		text: options.confirmButtonTitle || `Ok`,
		onPress: () => callback('confirmed')
	})
	if (options.showCancel) {
		buttons.push({
			text: options.otherButtonTitle || `Cancel`,
			onPress: () => callback('cancelled'),
			style: `cancel`
		})
	}

	const title = options.subTitle && options.title ? options.title : null
	const subTitle = options.title && !options.subTitle ? options.title : options.subTitle || null

	Alert.alert(title, subTitle, buttons)
}

export default CustomAlert