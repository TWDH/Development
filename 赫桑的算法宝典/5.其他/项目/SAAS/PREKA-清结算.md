# Order 表 

- 卡面值：$100
- 商家discount：$10
- 卖价：$90
- 数量
- 时间
- 来源platform



# Clearing 表 (流水)

- Discount：$10
- Commission (DR, out)： -$5
- Fee (CR, in)：+$5
- Settle1：$40
- Settle2：$40



# Merchant Clearing

| No.                   |      MerchantID      | Card Account ID | Direction | chart of account (FEE / Settlement1 / Settlement2 / Discount) | Finished | PeroidType |        Amount         | reverse transaction id(完全) | Related id | reference id | createTme |
| --------------------- | :------------------: | --------------- | :-------: | :----------------------------------------------------------: | :------: | :--------: | :-------------------: | :--------------------------: | ---------- | ------------ | :-------: |
| 1                     |      bubble tea      |                 |    CR     |                             FEE                              |          |            | 5 FEE (+5 Commission) |                              |            |              |           |
| 2                     |      bubble tea      |                 |    DR     |                         Settlement1                          |    1     |    week    |          40           |              5               |            |              |           |
| 3                     |      bubble tea      |                 |    DR     |                         Settlement2                          |          |    year    |          40           |                              |            |              |           |
| 4                     |      bubble tea      |                 |    CR     |                            Bonus                             |          |            |          10           |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
| condition1  （=）5    |      bubble tea      |                 |    CR     |                       anti-settlement1                       |          |            |          40           |              2               |            |              |           |
| 6                     |      bubble tea      |                 |    DR     |                            redeem                            |          |            |          40           |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
| settlement2 = redeem2 |      bubble tea      |                 |    CR     |                       anti-settlement2                       |          |            |          40           |                              |            |              |           |
|                       |      bubble tea      |                 |    DR     |                            redeem                            |          |            |          40           |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
| settlement2 < redeem2 |      bubble tea      |                 |    CR     |                                                              |          |            |                       |                              |            |              |           |
|                       |      bubble tea      |                 |    DR     |                                                              |          |            |                       |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
| condition 2（<）      |      bubble tea      |                 |    CR     |                       anti-settlement1                       |          |            |          40           |              5               |            |              |           |
|                       |      bubble tea      |                 |    DR     |                            redeem                            |          |            |          20           |                              |            |              |           |
|                       |      bubble tea      |                 |    DR     |                         Settlement1                          |          |            |          20           |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
| condition3（>）       |      bubble tea      |                 |    CR     |                       anti-settlement1                       |          |            |          40           |              2               |            |              |           |
|                       |      bubble tea      |                 |    CR     |                       anti-settlement2                       |          |            |          40           |              3               |            |              |           |
|                       |      bubble tea      |                 |    DR     |                            redeem                            |          |            |          60           |                              |            |              |           |
|                       |      bubble tea      |                 |    DR     |                         settlement2                          |          |            |          20           |                              |            |              |           |
|                       |                      |                 |           |                                                              |          |            |                       |                              |            |              |           |
| condition4-refund     | refund只冲redeem的账 |                 |           |                                                              |          |            |                       |                              |            |              |           |
|                       |        naicha        |                 |    CR     |                         anti-redeem                          |          |            |          40           |              6               |            |              |           |
|                       |        naicha        |                 |    CR     |                            refund                            |          |            |          32           |                              |            | 6            |           |
|                       |        naicha        |                 |    DR     |                            redeem                            |          |            |           8           |                              |            |              |           |













# Order Clearing



# Merchant Clearing