CREATE TABLE activity
(
  id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  description    VARCHAR(255),
  date           TIMESTAMP WITHOUT TIME ZONE,
  opportunity_id BIGINT,
  tenant_id      BIGINT,
  CONSTRAINT pk_activity PRIMARY KEY (id)
);

CREATE TABLE contacts
(
  id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name        VARCHAR(255),
  email       VARCHAR(255),
  phone       VARCHAR(255),
  customer_id BIGINT,
  tenant_id   BIGINT,
  CONSTRAINT pk_contacts PRIMARY KEY (id)
);

CREATE TABLE customers
(
  id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name      VARCHAR(255),
  tenant_id BIGINT,
  CONSTRAINT pk_customers PRIMARY KEY (id)
);

CREATE TABLE opportunities
(
  id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  title       VARCHAR(255),
  value       DOUBLE PRECISION,
  status      VARCHAR(255),
  stage       VARCHAR(255),
  customer_id BIGINT,
  tenant_id   BIGINT,
  CONSTRAINT pk_opportunities PRIMARY KEY (id)
);

CREATE TABLE tenant_configuration
(
  tenant_id   VARCHAR(255) NOT NULL,
  jdbc_url    VARCHAR(255),
  db_username VARCHAR(255),
  db_password VARCHAR(255),
  CONSTRAINT pk_tenant_configuration PRIMARY KEY (tenant_id)
);

CREATE TABLE tenants
(
  id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name   VARCHAR(255),
  domain VARCHAR(255),
  CONSTRAINT pk_tenants PRIMARY KEY (id)
);

CREATE TABLE "user"
(
  id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  username  VARCHAR(255),
  password  VARCHAR(255),
  email     VARCHAR(255),
  role      VARCHAR(255),
  tenant_id BIGINT,
  CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE activity
  ADD CONSTRAINT FK_ACTIVITY_ON_OPPORTUNITY FOREIGN KEY (opportunity_id) REFERENCES opportunities (id);

ALTER TABLE activity
  ADD CONSTRAINT FK_ACTIVITY_ON_TENANT FOREIGN KEY (tenant_id) REFERENCES tenants (id);

ALTER TABLE contacts
  ADD CONSTRAINT FK_CONTACTS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE contacts
  ADD CONSTRAINT FK_CONTACTS_ON_TENANT FOREIGN KEY (tenant_id) REFERENCES tenants (id);

ALTER TABLE customers
  ADD CONSTRAINT FK_CUSTOMERS_ON_TENANT FOREIGN KEY (tenant_id) REFERENCES tenants (id);

ALTER TABLE opportunities
  ADD CONSTRAINT FK_OPPORTUNITIES_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE opportunities
  ADD CONSTRAINT FK_OPPORTUNITIES_ON_TENANT FOREIGN KEY (tenant_id) REFERENCES tenants (id);

ALTER TABLE "user"
  ADD CONSTRAINT FK_USER_ON_TENANT FOREIGN KEY (tenant_id) REFERENCES tenants (id);