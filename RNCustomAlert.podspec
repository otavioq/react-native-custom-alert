Pod::Spec.new do |s|
  s.name         = "RNCustomAlert"
  s.version      = "1.1.3"
  s.summary      = "Custom Alert native library for React Native."
  s.homepage     = "https://github.com/otavioq/react-native-custom-alert.git"
  s.license      = 'MIT'
  s.author       = {'otavioq' => 'https://github.com/otavioq/react-native-custom-alert'}
  s.source       = { :git => 'https://github.com/otavioq/react-native-custom-alert.git',  :tag => "#{s.version}"}
  s.ios.deployment_target = '8.0'
  s.source_files = '*.swift'
  s.requires_arc = 'true'
end
