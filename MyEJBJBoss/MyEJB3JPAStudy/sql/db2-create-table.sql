CREATE
    TABLE TEST_CARD
    (
        CARDID VARCHAR(16) NOT NULL,
        PERSONID VARCHAR(32) NOT NULL,
        CARDNO VARCHAR(32) NOT NULL,
        PRIMARY KEY (CARDID)
    );
    
CREATE
    TABLE TEST_MSGENTITY
    (
        RPID VARCHAR(64) NOT NULL,
        MSG BLOB(1048576) NOT NULL,
        PRIMARY KEY (RPID)
    );

CREATE
    TABLE TEST_PERSON
    (
        PERSONID VARCHAR(32) NOT NULL,
        PERSONNAME VARCHAR(32) NOT NULL,
        PRIMARY KEY (PERSONID)
    );