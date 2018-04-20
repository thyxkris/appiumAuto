cd %source%\appiumAuto\
call mvn clean test -Dcucumber.options="--tags @android"
call mvn test -Dtest=report
target\cucumber-parallel\cucumber-html-reports\overview-steps.html
cmd /k