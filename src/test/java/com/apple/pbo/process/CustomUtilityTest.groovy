package com.apple.pbo.process

import spock.lang.Specification


/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 2/7/14
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
class CustomUtilityTest extends Specification {
    def defaultPathBase = new File( "." ).getCanonicalPath()
    def testfilespath   = defaultPathBase + "/testfiles"
    def customUtility   = new CustomUtility()

    def "test validateFile if file is valid"() {
        given:
        String s = testfilespath + "/points.txt"
        when:
        def result = customUtility.isValidFile(s);
        then:
        assert result == Boolean.TRUE
    }



    def "test validateFIle if filename is Empty"(){
        given:
        String s = ""
        when:
        def result = customUtility.isValidFile(s);
        then:
        assert result == Boolean.FALSE
    }


}