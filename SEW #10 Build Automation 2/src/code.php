<?php

function test($i) { return $i+42; };
$test = test($_GET['zahl']);
echo "Hello World $test";

?>