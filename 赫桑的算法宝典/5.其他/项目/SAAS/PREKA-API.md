# 获取卡实例

- Authorization：暂时无访问限制，后续会添加 appId 和 appKey

- api: GET: http://35.183.33.92:8088/api/partner/card

- Body (JSON 注意使用snake传值)

  - ```json
    {
        "order_id": "12345",
        "partner": "POCKET",
        "order_item_dto":{
            "sku":"InComm-VCAD-D-V-03",
            "quantity": "5"
        },
    }
    ```

  - `order_id:` pocket的订单号

  - `partner:` 请填写 "POCKET"

  - `order_item_dto.sku:` 商品 sku

  - `order_item_dto.quantity:` 商品数量

- Response (Example)

  - ```json
    {
        "success": true,
        "code": 20000,
        "message": "成功",
        "data": {
            "data": {
                "order_number": "266241278579310594",
                "sku": "InComm-VCAD-D-V-03",
                "quantity": 5,
                "card_detail": [
                    {
                        "card_number": "61230920797",
                        "card_pin": "581204"
                    },
                    {
                        "card_number": "61230084743",
                        "card_pin": "115518"
                    },
                    {
                        "card_number": "61230743686",
                        "card_pin": "586461"
                    },
                    {
                        "card_number": "61230258768",
                        "card_pin": "921263"
                    },
                    {
                        "card_number": "61230549166",
                        "card_pin": "570529"
                    }
                ],
                "batch": 1,
                "order_time": "2022-04-21 19:31:35",
                "deliver_time": "2022-04-21 19:31:35"
            }
        }
    }
    ```

- Exception
  - 暂未开发