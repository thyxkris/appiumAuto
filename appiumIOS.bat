cd %source%\appiumAuto\
call mvn clean test -Dcucumber.options="--tags @ios" -Dapp.absolute.path="/Users/kris/source/TestApp-iphonesimulator.app" -Dappium.server.url="http://192.168.233.128:4723/wd/hub" -Ddevice.name="iPhone X" -Dplatform.version=11.2
call mvn test -Dtest=report
target\cucumber-parallel\cucumber-html-reports\overview-steps.html
cmd /k