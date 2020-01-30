
### Development
- Development domain: https://facebook.github.io/react-native/docs/getting-started

### Installing dependencies

**Node & Watchman**:
```
brew install node
brew install watchman
```
**Java Development Kit**:
```
brew tap AdoptOpenJDK/openjdk
brew cask install adoptopenjdk8
```

**Android development environment**:
- Android SDK
- Android SDK Platform
- Performance (Intel ® HAXM) (See here for AMD)
- Android Virtual Device


**Configure the ANDROID_HOME environment variable**:
```
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/emulator
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/tools/bin
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

### Running the project
- pull project from repository.
- Open a macOS Terminal and go to the project directory(`./Awesome_Project`).
- if this is first start - run ``npm install`` command.
- run `restart.sh` script to clear cache and build project on emulator.




