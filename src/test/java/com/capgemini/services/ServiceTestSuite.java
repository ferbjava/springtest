package com.capgemini.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CarServiceTests.class, EmployeeServiceTests.class, PositionServiceTests.class})
public class ServiceTestSuite {

}
