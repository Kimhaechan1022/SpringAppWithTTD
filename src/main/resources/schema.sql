
DROP TABLE IF EXISTS NOTICE;

create table NOTICE(
    ID          BIGINT auto_increment primary key,
    TITLE       VARCHAR(255),
    CONTENT     VARCHAR(255),

    VIEW_CNT    INTEGER,
    LIKES       INTEGER,
    REG_DATE    TIMESTAMP
);