Pod::Spec.new do |s|
  s.name         = "RNCustomAlert"
  s.version      = "1.0.2"
  s.summary      = "Custom Alert native library for React Native."
  s.homepage     = "https://github.com/otavioq/rncustomalert.git"
  s.license      = 'MIT'
  s.author       = {'otavioq' => 'https://github.com/otavioq/rncustomalert'}
  s.source       = { :git => 'https://github.com/otavioq/rncustomalert.git',  :tag => "#{s.version}"}
  s.ios.deployment_target = '8.0'
  s.source_files = '*.swift'
  s.requires_arc = 'true'
end
