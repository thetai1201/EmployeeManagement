USE TestingSystem;
INSERT INTO Employee (name, gender, dateOfBirth, address, image, citizenId, phoneNumber, email, createBy)
VALUES 
('John Doe', 'Male', '1985-02-15', '123 Main St', 'john_doe.png', 'EMP001', '1234567890', 'john.doe@example.com','admin1'),
('Jane Smith', 'Female', '1990-05-25', '456 Another St', 'jane_smith.png', 'EMP002', '0987654321', 'jane.smith@example.com','admin1'),
('Alice Johnson', 'Female', '1987-08-17', '789 Side St', 'alice_johnson.png', 'EMP003', '1122334455', 'alice.johnson@example.com','admin1'),
('Bob Brown', 'Male', '1992-11-30', '321 Any St', 'bob_brown.png', 'EMP004', '6677889900', 'bob.brown@example.com','admin1'),
('Charlie Davis', 'Male', '1980-03-20', '654 Random St', 'charlie_davis.png', 'EMP005', '4455667788', 'charlie.davis@example.com','admin1');

INSERT INTO Certificate (employeeId, title, field, issuedDate, description)
VALUES
(1, 'Certified Java Developer', 'Programming', '2010-06-15', 'Oracle Certified Professional, Java SE 6 Programmer'),
(2, 'Certified Project Manager', 'Management', '2012-08-20', 'Project Management Professional (PMP)'),
(3, 'Certified Data Analyst', 'Data Science', '2015-09-25', 'Certified Analytics Professional (CAP)'),
(4, 'Certified Network Associate', 'Networking', '2018-11-10', 'Cisco Certified Network Associate (CCNA)'),
(5, 'Certified Cloud Practitioner', 'Cloud Computing', '2020-01-30', 'AWS Certified Cloud Practitioner');

INSERT INTO Relationship (employeeId, name, gender, dateOfBirth, address, citizenId, phoneNumber, job, relationship)
VALUES 
(1,'John Doe', 'Male', '1980-05-15', '123 Main St', 'FR1234567890', '5551234567', 'Engineer', 'Brother'),
(2,'Jane Smith', 'Female', '1990-08-22', '456 Elm St', 'FR0987654321', '5559876543', 'Doctor', 'Sister'),
(3,'Alice Johnson', 'Female', '1975-12-01', '789 Oak St', 'FR1122334455', '5551122334', 'Teacher', 'Mother'),
(4,'Bob Brown', 'Male', '1965-03-30', '321 Maple St', 'FR2233445566', '5552233445', 'Manager', 'Father');

INSERT INTO `Role` (name) VALUES ('MANAGER'), ('LEADER');

INSERT INTO `User` (username, password, roleId, name, position, email, phoneNumber) VALUES
('admin1', '$2a$10$Dow7K1m1axDAFfCe7OhiUu5V5OZZ/WFJ4M6uUuLX.Kt/E/hi7WbWC', 1, 'Admin User', 'Admin Position', 'admin@example.com', '1234567890'),
('admin2', 'password1', 1, 'User One', 'Position 1', 'user1@example.com', '1234567891'),
('admin3', 'password2', 1, 'User Two', 'Position 2', 'user2@example.com', '1234567892'),
('admin4', 'password3', 1, 'User Three', 'Position 3', 'user3@example.com', '1234567893'),
('admin5', 'password4', 1, 'User Four', 'Position 4', 'user4@example.com', '1234567894'),
('leader1', 'leader', 2, 'User Five', 'Position 5', 'user5@example.com', '1234567895'),
('leader2', 'password6', 2, 'User Six', 'Position 6', 'user6@example.com', '1234567896'),
('leader3', 'password7', 2, 'User Seven', 'Position 7', 'user7@example.com', '1234567897'),
('leader4', 'password8', 2, 'User Eight', 'Position 8', 'user8@example.com', '1234567898'),
('leader5', 'password9', 2, 'User Nine', 'Position 9', 'user9@example.com', '1234567899');

-- Insert data into tbl_registration
INSERT INTO Registration (employeeId, leaderId, createDate, createBy, content, status, submitDate, rejectDate, rejectReason, acceptDate, note)
VALUES
(1, 6, '2024-03-22', 'admin1', 'Registration content 1', 'Pending', '2024-03-22', NULL, NULL, NULL, 'Note for registration 1'),
(2, 7, '2024-03-23', 'admin2', 'Registration content 2', 'Pending', '2024-03-24',  NULL, NULL, NULL, 'Note for registration 2');

INSERT INTO Profile_end (registrationId, leaderId, endDate, endBy, reason, status, rejectDate, rejectReason, acceptDate, storageNumber)
VALUES
(1, 6, '2024-03-22', 'admin1', 'Resignation', 'Pending', NULL, NULL, NULL, 1),
(2, 6, '2024-03-23', 'admin1', 'Retirement', 'Pending', NULL, NULL, NULL, 2);
