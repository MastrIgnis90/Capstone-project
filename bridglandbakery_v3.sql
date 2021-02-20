#Database creation and table creation for the bridgeland bakery 
#web application, this script creates the database, as well as 
#creations the table structure for 6 different tables, with their
#respective constraints. 
#Author: Alex Peluso
#Date: Feb 2th, 2021

#Create DATABASE
CREATE DATABASE IF NOT EXISTS bridgelandbakery;
USE bridgelandbakery;

#Drop tables
DROP TABLE IF EXISTS employeeauthentication;
DROP TABLE IF EXISTS orderitems;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS customertype;

#Creation of the Customer Type table
#The purpose of this table is to track the different types
#of customers
CREATE TABLE customertype (
	customer_type CHAR(1) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
);

#Creation of the Customer table
#Purpose of this table is to track the customer information
CREATE TABLE customer (
	customer_id BIGINT(8) PRIMARY KEY,
	last_name VARCHAR(24) NOT NULL,
	first_name VARCHAR(24) NOT NULL,
	customer_type CHAR(1),
	street_address VARCHAR (24),
	community VARCHAR(24),
	postal_code VARCHAR(6) NOT NULL,
	email VARCHAR(24) NOT NULL UNIQUE,
	phone_number BIGINT(10) NOT NULL,
	customer_status CHAR(1),
	CONSTRAINT fk_customer_type FOREIGN KEY (customer_type) REFERENCES customertype(customer_type)
);

#Creation of the Product table
#Purpose of this table is to store the information for 
#each product and their current stats.
CREATE TABLE products (
	product_code VARCHAR(6) PRIMARY KEY,
	product_name VARCHAR(20) NOT NULL,
	productprice DOUBLE(5,2) NOT NULL,
	category VARCHAR(20),
	product_description VARCHAR(50),
	product_status CHAR(1) NOT NULL
);

#Creation of the Order table
#tracks all the order information for a particular customer,
#will show special notes as needed
CREATE TABLE orders (
	order_id BIGINT(8) PRIMARY KEY,
	customer_id BIGINT(8),
	order_date DATE NOT NULL,
	delivery_date DATE NOT NULL,
	price_total DOUBLE(4,2),
	standing_order CHAR(1) NOT NULL,
	order_status CHAR(1),
	order_notes VARCHAR(50),
	CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

#Creation of the Order items table
#this will provide an itemized list of products for each order
#this is the table that also creates the final price
CREATE TABLE orderitems (
	order_id BIGINT(8),
	iten_number INT(2),
	product_code VARCHAR(6),
	quantity_delivered INT(3) NOT NULL,
	quantity_sold INT (3),
	price_item DOUBLE(5,2) NOT NULL,
	PRIMARY KEY (order_id,iten_number),
	CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders(order_id),
	CONSTRAINT fk_product_code FOREIGN KEY (product_code) REFERENCES products(product_code)
);

#Creation of the Employee Authentication table
#The 0 value will be for a regular employee or manager user
#The 1 value will be for an admin superuser
CREATE TABLE employeeauthentication (
	employee_id BIGINT(4) PRIMARY KEY,
	employee_access_level BIGINT(1) NOT NULL
);

