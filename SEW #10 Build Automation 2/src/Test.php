<?php
/**
 * Name of file  :	Test.php
 * Author        : 	Stefan Erceg <serceg@student.tgm.ac.at>
 * Version       : 	20150121
 * Created       : 	20150115
 * Description   :	Created a simple test-case for a function that returns a number.
 */

class Test extends PHPUnit_Framework_TestCase {

	public function testPrintNumber() {
		require_once "code.php";
		$GET['zahl'] = 42;
		$this->expectOutputString('Hello World 84');
	}

}

?>