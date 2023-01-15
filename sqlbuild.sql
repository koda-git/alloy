create table bank_accounts
(
    uuid          char(36) not null
        primary key,
    ownerUUID     char(36) null,
    accountNumber text     null,
    routingNumber text     null,
    bankName      text     null,
    type          text     null,
    name          text     null,
    balance       float    null,
    currency      text     null
);

create table board
(
    uuid         char(36) not null
        primary key,
    name         text     null,
    description  text     null,
    categoryUUID char(36) null,
    lastUpdated  text     null
);

create table category
(
    uuid        char(36) not null
        primary key,
    name        text     null,
    description text     null,
    iconPath    text     null
);

create table comments
(
    uuid       char(36)   not null
        primary key,
    content    text       null,
    authorUUID char(36)   null,
    authorName text       null,
    postUUID   char(36)   null,
    postTime   mediumtext null,
    likes      int        null,
    dislikes   int        null
);

create table posts
(
    id         int auto_increment
        primary key,
    title      text       null,
    content    text       null,
    authorUUID char(36)   null,
    authorName text       null,
    boardUUID  char(36)   null,
    postTime   mediumtext null,
    likes      int        null,
    dislikes   int        null,
    year       int        null,
    month      int        null,
    day        int        null,
    hour       int        null,
    minute     int        null,
    second     int        null
);

create table transactions
(
    hashRecord      char(36)   not null
        primary key,
    ownerUUID       char(36)   null,
    senderAccount   char(36)   null,
    receiverAccount char(36)   null,
    amount          float      null,
    currency        text       null,
    transactionName text       null,
    transactionTime mediumtext null,
    year            int        null,
    month           int        null,
    day             int        null,
    hour            int        null,
    minute          int        null,
    second          int        null,
    ownerAccount    text       null
);

create table users
(
    lastName         text     not null,
    firstName        text     not null,
    email            text     not null,
    password         text     not null,
    uuid             char(36) not null
        primary key,
    phoneNumber      text     not null,
    gender           text     not null,
    verificationCode text     not null,
    verified         text     not null,
    address          text     not null
);

