DELETE FROM tusermain WHERE user_id = '0B100' ;
DELETE FROM tsavings WHERE id = '123' ;
INSERT INTO `tusermain`(user_id,active,authorities,password,user_name,email) VALUES ('0B100', 'Y', 'user', '9d8f988f8ae8d0ac7bc3195c7c9350a5', 'TOMMY TANSEN', 'test@awan.com');
INSERT INTO `tsavings`(id,acc_no,balance,user_id) VALUES ('123','0011223344',5000,'0B100');