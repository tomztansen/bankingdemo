# bankingdemo
#ketentuan :
- data sudah diinialisasi sebelum bisa digunakan, dalam ini ada user:0B100, password : 12345678.
- harus login terlebih dahulu untuk mendapat token
- setelah itu baru bisa menggunakan beberapa fitur yaitu : daftar, deposit, withdraw dan lihat transaksi.

## test login :
```curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d "{"""username""":"""0B100""","""password""":"""12345678"""}" http://localhost:8080/login-auth```

### contoh "X-Auth-Token: ```eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwQjEwMCIsImF1ZGllbmNlIjoidW5rbm93biIsIml1c2VybmFtZSI6IlRPTU1ZIFRBTlNFTiIsImNyZWF0ZWQiOjE1MjY2MTQ4MDc3MjAsImV4cCI6MTUyNjYxNTQxMn0.CwR-GYEtPhCyHOio0zSu59MYRzNaR3YK1mgmkMSqj_Sha45oZ83Xk3u17nDPxZdzFoqXoM6NXwanhe677hPk7Q"```

## test daftar :
```curl -i -H "Accept: application/json" -H "Content-type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwQjEwMCIsImF1ZGllbmNlIjoidW5rbm93biIsIml1c2VybmFtZSI6IlRPTU1ZIFRBTlNFTiIsImNyZWF0ZWQiOjE1MjY2MTQ4MDc3MjAsImV4cCI6MTUyNjYxNTQxMn0.CwR-GYEtPhCyHOio0zSu59MYRzNaR3YK1mgmkMSqj_Sha45oZ83Xk3u17nDPxZdzFoqXoM6NXwanhe677hPk7Q"  -X POST -d "{"""userid""":"""0B101""","""username""":"""BUDI""","""password""":"""12345678""","""email""":"""test@gmail.com""","""role""":"""ADMIN""","""accno""":"""1234567890""","""balance""":500000}" http://localhost:8080/bank/register/web```

## test deposit :
```curl -i -H "Accept: application/json" -H "Content-type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwQjEwMSIsImF1ZGllbmNlIjoidW5rbm93biIsIml1c2VybmFtZSI6IkJVREkiLCJjcmVhdGVkIjoxNTI2NjE1Njg5NDY1LCJleHAiOjE1MjY2MTYyOTR9.EbaolG3qI1CzfIbUBQfEgd8uDkE15yi2mUFBxSWVvodyCe82QSnDH-9nL4Zhk5gmvAAS594i0adVQrZo4umwxA"  -X PUT "http://localhost:8080/bank/deposit/web?amount=1&accno=1234567890"```

## test withdraw :
```curl -i -H "Accept: application/json" -H "Content-type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwQjEwMSIsImF1ZGllbmNlIjoidW5rbm93biIsIml1c2VybmFtZSI6IkJVREkiLCJjcmVhdGVkIjoxNTI2NjE1Njg5NDY1LCJleHAiOjE1MjY2MTYyOTR9.EbaolG3qI1CzfIbUBQfEgd8uDkE15yi2mUFBxSWVvodyCe82QSnDH-9nL4Zhk5gmvAAS594i0adVQrZo4umwxA"  -X PUT "http://localhost:8080/bank/withdraw/web?amount=1&accno=1234567890"```

## test transfer :
```curl -i -H "Accept: application/json" -H "Content-type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwQjEwMSIsImF1ZGllbmNlIjoidW5rbm93biIsIml1c2VybmFtZSI6IkJVREkiLCJjcmVhdGVkIjoxNTI2NjE1Njg5NDY1LCJleHAiOjE1MjY2MTYyOTR9.EbaolG3qI1CzfIbUBQfEgd8uDkE15yi2mUFBxSWVvodyCe82QSnDH-9nL4Zhk5gmvAAS594i0adVQrZo4umwxA"  -X PUT "http://localhost:8080/bank/transfer/web?amount=2&fromaccno=1234567890&toaccno=0011223344"```

## test view :
```curl -i -H "Accept: application/json" -H "Content-type: application/json" -H "X-Auth-Token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwQjEwMSIsImF1ZGllbmNlIjoidW5rbm93biIsIml1c2VybmFtZSI6IkJVREkiLCJjcmVhdGVkIjoxNTI2NjE1Njg5NDY1LCJleHAiOjE1MjY2MTYyOTR9.EbaolG3qI1CzfIbUBQfEgd8uDkE15yi2mUFBxSWVvodyCe82QSnDH-9nL4Zhk5gmvAAS594i0adVQrZo4umwxA" -X GET "http://localhost:8080/bank/view/web?accno=1234567890"```
