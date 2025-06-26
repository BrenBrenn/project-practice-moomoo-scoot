# Ice Cream Shop Database Design Document

| No. | Table Name     | English Name      |
| --- | -------------- | ----------------- |
| 1   | employee       | Staff Table       |
| 2   | category       | Category Table    |
| 3   | dish           | Ice Cream Items   |
| 4   | dish_flavor    | Toppings Table    |
| 5   | setmeal        | Combo Table       |
| 6   | setmeal_dish   | Combo Items Table |
| 7   | user           | Customer Table    |
| 8   | address_book   | Address Table     |
| 9   | shopping_cart  | Cart Table        |
| 10  | orders         | Order Table       |
| 11  | order_detail   | Order Details     |

### 1. employee

The employee table stores staff information for the ice cream shop. Table structure:

| Field Name   | Data Type    | Description    | Notes           |
| ------------ | ------------ | -------------- | --------------- |
| id           | bigint       | Primary Key    | Auto Increment  |
| name         | varchar(32)  | Name           |                 |
| username     | varchar(32)  | Username       | Unique          |
| password     | varchar(64)  | Password       |                 |
| phone        | varchar(11)  | Phone Number   |                 |
| sex          | varchar(2)   | Gender         |                 |
| id_number    | varchar(18)  | ID Number      |                 |
| status       | int          | Account Status | 1 Active 0 Locked |
| create_time  | datetime     | Create Time    |                 |
| update_time  | datetime     | Update Time    |                 |
| create_user  | bigint       | Creator ID     |                 |
| update_user  | bigint       | Updater ID     |                 |

### 2. category

The category table stores product category information. Table structure:

| Field Name   | Data Type    | Description    | Notes                    |
| ------------ | ------------ | -------------- | ------------------------ |
| id           | bigint       | Primary Key    | Auto Increment           |
| name         | varchar(32)  | Category Name  | Unique                   |
| type         | int          | Category Type  | 1 Item Category 2 Combo Category |
| sort         | int          | Sort Order     | For category sorting     |
| status       | int          | Status         | 1 Enabled 0 Disabled     |
| create_time  | datetime     | Create Time    |                          |
| update_time  | datetime     | Update Time    |                          |
| create_user  | bigint       | Creator ID     |                          |
| update_user  | bigint       | Updater ID     |                          |

### 3. dish

The dish table stores ice cream item information. Table structure:

| Field Name   | Data Type      | Description    | Notes           |
| ------------ | -------------- | -------------- | --------------- |
| id           | bigint         | Primary Key    | Auto Increment  |
| name         | varchar(32)    | Item Name      | Unique          |
| category_id  | bigint         | Category ID    | Logical Foreign Key |
| price        | decimal(10,2)  | Item Price     |                 |
| image        | varchar(255)   | Image Path     |                 |
| description  | varchar(255)   | Description    |                 |
| status       | int            | Sale Status    | 1 Available 0 Unavailable |
| create_time  | datetime       | Create Time    |                 |
| update_time  | datetime       | Update Time    |                 |
| create_user  | bigint         | Creator ID     |                 |
| update_user  | bigint         | Updater ID     |                 |

### 4. dish_flavor

The dish_flavor table stores ice cream toppings and preferences. Table structure:

| Field Name | Data Type     | Description    | Notes     |
| ---------- | ------------- | -------------- | --------- |
| id         | bigint        | Primary Key    | Auto Increment |
| dish_id    | bigint        | Item ID        | Logical Foreign Key |
| name       | varchar(32)   | Topping Name   |           |
| value      | varchar(255)  | Topping Value  |           |

### 5. setmeal

The setmeal table stores combo information. Table structure:

| Field Name   | Data Type      | Description    | Notes           |
| ------------ | -------------- | -------------- | --------------- |
| id           | bigint         | Primary Key    | Auto Increment  |
| name         | varchar(32)    | Combo Name     | Unique          |
| category_id  | bigint         | Category ID    | Logical Foreign Key |
| price        | decimal(10,2)  | Combo Price    |                 |
| image        | varchar(255)   | Image Path     |                 |
| description  | varchar(255)   | Description    |                 |
| status       | int            | Sale Status    | 1 Available 0 Unavailable |
| create_time  | datetime       | Create Time    |                 |
| update_time  | datetime       | Update Time    |                 |
| create_user  | bigint         | Creator ID     |                 |
| update_user  | bigint         | Updater ID     |                 |

### 6. setmeal_dish

The setmeal_dish table stores the relationship between combos and items. Table structure:

| Field Name  | Data Type      | Description    | Notes     |
| ----------- | -------------- | -------------- | --------- |
| id          | bigint         | Primary Key    | Auto Increment |
| setmeal_id  | bigint         | Combo ID       | Logical Foreign Key |
| dish_id     | bigint         | Item ID        | Logical Foreign Key |
| name        | varchar(32)    | Item Name      | Redundant Field |
| price       | decimal(10,2)  | Item Price     | Redundant Field |
| copies      | int            | Item Quantity  |           |

### 7. user

The user table stores customer information. Table structure:

| Field Name   | Data Type     | Description           | Notes |
| ------------ | ------------- | --------------------- | ----- |
| id           | bigint        | Primary Key           | Auto Increment |
| openid       | varchar(45)   | Customer Unique ID    |       |
| name         | varchar(32)   | Customer Name         |       |
| phone        | varchar(11)   | Phone Number          |       |
| sex          | varchar(2)    | Gender                |       |
| id_number    | varchar(18)   | ID Number             |       |
| avatar       | varchar(500)  | Customer Avatar Path  |       |
| create_time  | datetime      | Registration Time     |       |

### 8. address_book

The address_book table stores customer delivery address information. Table structure:

| Field Name     | Data Type     | Description    | Notes              |
| -------------- | ------------- | -------------- | ------------------ |
| id             | bigint        | Primary Key    | Auto Increment     |
| user_id        | bigint        | Customer ID    | Logical Foreign Key |
| consignee      | varchar(50)   | Recipient      |                    |
| sex            | varchar(2)    | Gender         |                    |
| phone          | varchar(11)   | Phone Number   |                    |
| province_code  | varchar(12)   | Province Code  |                    |
| province_name  | varchar(32)   | Province Name  |                    |
| city_code      | varchar(12)   | City Code      |                    |
| city_name      | varchar(32)   | City Name      |                    |
| district_code  | varchar(12)   | District Code  |                    |
| district_name  | varchar(32)   | District Name  |                    |
| detail         | varchar(200)  | Detailed Address| Specific to door number |
| label          | varchar(100)  | Label          | Company, Home, School |
| is_default     | tinyint(1)    | Default Address| 1 Yes 0 No        |

### 9. shopping_cart

The shopping_cart table stores customer shopping cart information. Table structure:

| Field Name   | Data Type      | Description    | Notes     |
| ------------ | -------------- | -------------- | --------- |
| id           | bigint         | Primary Key    | Auto Increment |
| name         | varchar(32)    | Item Name      |           |
| image        | varchar(255)   | Item Image Path|           |
| user_id      | bigint         | Customer ID    | Logical Foreign Key |
| dish_id      | bigint         | Item ID        | Logical Foreign Key |
| setmeal_id   | bigint         | Combo ID       | Logical Foreign Key |
| dish_flavor  | varchar(50)    | Item Toppings  |           |
| number       | int            | Quantity       |           |
| amount       | decimal(10,2)  | Item Price     |           |
| create_time  | datetime       | Create Time    |           |

### 10. orders

The orders table stores customer order data. Table structure:

| Field Name              | Data Type      | Description    | Notes                                            |
| ----------------------- | -------------- | -------------- | ------------------------------------------------ |
| id                      | bigint         | Primary Key    | Auto Increment                                   |
| number                  | varchar(50)    | Order Number   |                                                  |
| status                  | int            | Order Status   | 1 Pending Payment 2 Pending Acceptance 3 Accepted 4 Delivering 5 Completed 6 Cancelled |
| user_id                 | bigint         | Customer ID    | Logical Foreign Key                              |
| address_book_id         | bigint         | Address ID     | Logical Foreign Key                              |
| order_time              | datetime       | Order Time     |                                                  |
| checkout_time           | datetime       | Payment Time   |                                                  |
| pay_method              | int            | Payment Method | 1 Card 2 paypal                                  |
| pay_status              | tinyint        | Payment Status | 0 Unpaid 1 Paid 2 Refunded                       |
| amount                  | decimal(10,2)  | Order Amount   |                                                  |
| remark                  | varchar(100)   | Remarks        |                                                  |
| phone                   | varchar(11)    | Phone Number   |                                                  |
| address                 | varchar(255)   | Address        |                                                  |
| user_name               | varchar(32)    | Customer Name  |                                                  |
| consignee               | varchar(32)    | Recipient      |                                                  |
| cancel_reason           | varchar(255)   | Cancel Reason  |                                                  |
| rejection_reason        | varchar(255)   | Rejection Reason|                                                 |
| cancel_time             | datetime       | Cancel Time    |                                                  |
| estimated_delivery_time | datetime       | Estimated Delivery Time |                                         |
| delivery_status         | tinyint        | Delivery Status| 1 Immediate Delivery 0 Scheduled Time            |
| delivery_time           | datetime       | Delivery Time  |                                                  |
| pack_amount             | int            | Packing Fee    |                                                  |
| tableware_number        | int            | Utensils Count |                                                  |
| tableware_status        | tinyint        | Utensils Status| 1 By Order Quantity 0 Specific Count             |

### 11. order_detail

The order_detail table stores customer order detail data. Table structure:

| Field Name   | Data Type      | Description    | Notes     |
| ------------ | -------------- | -------------- | --------- |
| id           | bigint         | Primary Key    | Auto Increment |
| name         | varchar(32)    | Item Name      |           |
| image        | varchar(255)   | Item Image Path|           |
| order_id     | bigint         | Order ID       | Logical Foreign Key |
| dish_id      | bigint         | Item ID        | Logical Foreign Key |
| setmeal_id   | bigint         | Combo ID       | Logical Foreign Key |
| dish_flavor  | varchar(50)    | Item Toppings  |           |
| number       | int            | Quantity       |           |
| amount       | decimal(10,2)  | Item Price     |           |

