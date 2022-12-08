

SELECT EncryptedData = CONVERT(VARCHAR(32), HashBytes('MD5', '12345'), 2)

select EncryptedData = EncryptByPassPhrase('maiquocbao123', '123456')

insert into TaiKhoan values ( 'NV005', 'maiquocbao123', CONVERT(VARCHAR(255), EncryptByPassPhrase('maiquocbao123', '123456'), 1), 'ADMIN')

select EncryptedData = EncryptByPassPhrase('maiquocbao123', '123456' )

select convert(varchar(100),DecryptByPassPhrase('maiquocbao123', 0x02000000E77270B5447FBD5DDE0022721EC96BAF45EBE49B2E5159B71FDDCB211DFF2F88)) as giaima