package com.apple.pbo.process

import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 2/7/14
 * Time: 12:12 AM
 * Spock framework Rocks
 */
class ProcessBoxPointsTest extends Specification {
    def processBoxPoints= new ProcessBoxPoints()
    def customUtility = new CustomUtility()
    def defaultPathBase = new File( "." ).getCanonicalPath()
    def testfilespath   = defaultPathBase + "/testfiles"

    //Point related tests
    def "test isValidPoint with a valid point"() {
        given:
              String point = "point 7.08 0.64 358b7990-387c-43a5-92d8-b454a727bf63"
        when:
           def result = processBoxPoints.isValidPoint(point)
        then:
            assert result == Boolean.TRUE
    }



    def "test isValidPoint with an invalid point"() {
        given:
        String point = "point 7.08 xxxx 358b7990-387c-43a5-92d8-b454a727bf63"
        when:
        def result = processBoxPoints.isValidPoint(point)
        then:
        assert result == Boolean.FALSE
    }
    // def "test convertToiPoint


    def "test convertToiPoint with a valid pointline"(){
        given:
        String point = "point 7.08 0.64 358b7990-387c-43a5-92d8-b454a727bf63"
        when:
        assert processBoxPoints.isValidPoint(point) == Boolean.TRUE
        def endPoint = processBoxPoints.convertToiPoint(point)
        then:
            endPoint.pointID     == "358b7990-387c-43a5-92d8-b454a727bf63"
            endPoint.xCoordinate == 7.08
            endPoint.yCoordinate == 0.64

    }
    //we should not get this far if it's invalid , but good to have a test case
    def "test convertToiPoint with a invalid point line"(){
        given:
        String point = "point 7.08 x.64 358b7990-387c-43a5-92d8-b454a727bf63"
        when:
        processBoxPoints.isValidPoint(point) == Boolean.TRUE
        def endPoint = processBoxPoints.convertToiPoint(point)
        then:
        thrown(NumberFormatException)
        endPoint == null

    }

    def "test isValidBox with a Valid Box "() {
        given:
            String box = "box 7.2 2.0 7.3 0.5"
        when:
            def result = processBoxPoints.isValidBox(box)
        then:
            assert result == Boolean.TRUE
    }


    def "test isValidBox with a inValid Box"() {
        given:
        String box = "boxes tripped 2.0 7.3 0.5"
        when:
        def result = processBoxPoints.isValidBox(box)
        then:
        assert result == Boolean.FALSE
    }


    def "test convertToiBox with a valid Box line"() {
        given:
        String box = "box 7.2 2.0 7.3 0.5"
        when:
        processBoxPoints.isValidBox(box) == Boolean.TRUE
        def endBox = processBoxPoints.converToiBox(box)
        then:
        endBox.xCorFirst  == 7.2
        endBox.xCorSecond == 7.3
        endBox.yCorFirst  == 2.0
        endBox.yCorSecond == 0.5
    }
    def "test isDouble with a valid double value"() {
        given:
        String s = "0.25"
        when:
        def result = processBoxPoints.isDouble(s);
        then:

        assert result == Boolean.TRUE

    }

    def "test isDouble with a invalid value"() {
        given:
        String s = "invalid"
        when:
        def result = processBoxPoints.isDouble(s);
        then:

        assert result == Boolean.FALSE

    }
    def "test isInBounds"()
    {
        given:
        def point1 = processBoxPoints.convertToiPoint("point 7.08 0.64 358b7990-387c-43a5-92d8-b454a727bf63")
        def point2 = processBoxPoints.convertToiPoint("point -8.87 -1.00 172e5c20-041a-441b-a036-59fa08c48525")
        def point3 = processBoxPoints.convertToiPoint("point 7.23 1.92 ea1dd912-b4a2-490a-8969-65e45d25e7cf")
        def box    = processBoxPoints.converToiBox("box 7 0 8 2")
        expect:
            processBoxPoints.isInBounds(point1,box) == Boolean.TRUE
            processBoxPoints.isInBounds(point2,box) == Boolean.FALSE
            processBoxPoints.isInBounds(point3,box) == Boolean.TRUE



    }

    def "test processPoints with valid values"(){
        given:
            String file = testfilespath + "/points.txt"
            customUtility.isValidFile(file) == Boolean.TRUE
            def filebuffer = customUtility.loadData(file)
            processBoxPoints.validateData(filebuffer) == Boolean.TRUE
        when:
            def result = processBoxPoints.processPointsBox(filebuffer)
        then:
            result.size() == 10
            result.get(0) == "box 7 0 8 2"
            result.get(8) == "da7790e3-4904-44a5-a02f-0384fcaef902"

    }



}
