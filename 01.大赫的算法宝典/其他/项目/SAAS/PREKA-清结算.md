# Order 表 

- 卡面值：$100
- 商家discount：$10
- 卖价：$90
- 数量
- 时间
- 来源platform



# Clearing 表 (流水)

- Discount：$10
- Commission (DR, out)：-$5
- Fee (CR, in)：+$5
- Settle1：$40
- Settle2：$40

Merchant Clearing

| No.               |      MerchantID      | Card Account ID | Direction | chart of account (FEE / Settlement1 / Settlement2 / Discount) |        Amount         | reverse transcation id | reference id | TIme |      |
| ----------------- | :------------------: | --------------- | :-------: | :----------------------------------------------------------: | :-------------------: | :--------------------: | ------------ | :--: | :--: |
| 1                 |        naicha        |                 |    CR     |                             FEE                              | 5 FEE (+5 Commission) |                        |              |      |      |
| 2                 |        naicha        |                 |    DR     |                         Settlement1                          |          40           |           5            |              |      |      |
| 3                 |        naicha        |                 |    DR     |                         Settlement2                          |          40           |                        |              |      |      |
| 4                 |        naicha        |                 |    CR     |                           Discount                           |          10           |                        |              |      |      |
|                   |                      |                 |           |                                                              |                       |                        |              |      |      |
| condition1  5     |        naicha        |                 |    CR     |                       anti-settlement1                       |          40           |           2            |              |      |      |
| 6                 |        naicha        |                 |    DR     |                            redeem                            |          40           |                        |              |      |      |
|                   |                      |                 |           |                                                              |                       |                        |              |      |      |
| condition 2       |        naicha        |                 |    CR     |                       anti-settlement1                       |          40           |                        |              |      |      |
|                   |        naicha        |                 |    DR     |                            redeem                            |          20           |                        |              |      |      |
|                   |        naicha        |                 |    DR     |                         Settlement1                          |          20           |                        |              |      |      |
|                   |                      |                 |           |                                                              |                       |                        |              |      |      |
| condition3        |        naicha        |                 |    CR     |                       anti-settlement1                       |          40           |                        |              |      |      |
|                   |        naicha        |                 |    CR     |                       anti-settlement2                       |          40           |                        |              |      |      |
|                   |        naicha        |                 |    DR     |                            redeem                            |          60           |                        |              |      |      |
|                   |        naicha        |                 |    DR     |                         settlement2                          |          20           |                        |              |      |      |
|                   |                      |                 |           |                                                              |                       |                        |              |      |      |
| condition4-refund | refund只冲redeem的账 |                 |           |                                                              |                       |                        |              |      |      |
|                   |        naicha        |                 |    CR     |                         anti-redeem                          |          40           |           6            |              |      |      |
|                   |        naicha        |                 |    CR     |                            refund                            |          32           |                        | 6            |      |      |
|                   |        naicha        |                 |    DR     |                            redeem                            |           8           |                        |              |      |      |













# Order Clearing



# Merchant Clearing