<?php
$servername = "localhost";
$username = "root";
$password = ""; // Default password is empty for XAMPP
$dbname = "bitscon2024";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Prepare and bind SQL statement
$stmt = $conn->prepare("INSERT INTO attendance (firstname, midinitial, lastname, school, Phonenum) VALUES (?, ?, ?, ?, ?)");

// Bind parameters
$stmt->bind_param("sssss", $firstname, $midinitial, $lastname, $school, $Phonenum);

// Get form data
$firstname = $_POST['firstname'];
$midinitial = $_POST['midinitial'];
$lastname = $_POST['lastname'];
$school = $_POST['school'];
$Phonenum = $_POST['Phonenum'];

// Execute the statement
if ($stmt->execute()) {
    echo "New record created successfully";
} else {
    echo "Error: " . $stmt->error;
}

// Close the statement and connection
$stmt->close();
$conn->close();
?>
