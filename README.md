# Teaching-HEIGVD-RES-2016-Labo-SMTP

## Members

* **Sébstien Richoz**, sebastien.richoz1@heig-vd.ch
* **Damien Rochat**, damien.rochat@heig-vd.ch

## Description

This program is responsible for sending fake messages to a list of emails victims.

From lists of emails and messages, the application generate random groups of N emails and one message. One email is the sender, the others are the recipients.

## Configuration

Three configuration files allow to set application parameters (resources/config.properties) to define the list of emails (resources/emails.utf8) and define the messages to send (resources/messages.utf8).

The config.properties file must define : 

* **smtpServerAddress** and **smtpServerPort** : the SMTP configuration
* **sendBCCTo** : an email address that will receive a blinded carbon copy of all emails sent
* **numberOfGroups** : the number of group to create (a group corresponding to a prank).

## Execution

From the time when the configuration files have been filled, the application can be executed without arguments.

The project use Maven as build system :

* Build the project with the command : **mvn clean package**
* Execute the application with : **java -jar target/MailPranker-1.0.jar**
* Laugh!

## Execution of MockMock as SMTP test server

The MockMock project could be cloned from the repo : <https://github.com/tweakers-dev/MockMock>.

It provide a SMTP server that will intercept all the passing emails and provide a web interface to see them.
You need to build the project and execute the jar file to launch the server.

There is some options to define custom ports :

* **-p 2525** : the SMTP server will listen on the port 2525
* **-h 8080** : the HTTP server (for the web interface) will listen on the port 8080

For example : **java -jar MockMock-1.4.0.one-jar.jar -p 2525 -h 8080**

### Docker/Vagrant warning

If you use a virtualised environment, don't forget to map the ports of your VM to your host. For example, you can execute PrankMailer and MockMock inside Vagrant and access the MockMock web interface with your favorite browser if you map your 8080 port to the Vagrant 8080 port.

## Implementation

![Class diagram](./figures/class_diagram.png)

There is three packages, for the config classes, the smtp classes, and the PrankMailer specifics classes.

### Implementation details ####

The three packages shows the main structure of the project. Each package can be shown as a responsibility of the application.
There is one more MailPranker object out of packages which goal is to initiate the application. It gets the configurations from config file using the ConfigLoader and Config objects, generates the pranks with the PrankGenerator, and configure an SMTP connection to send the pranks.

The config package is responsible to load informations stored in the different files. ConfigLoader, MessagesLoader and EmailsLoader loads respectively configs, messages, and emails.

The purpose of model package is to generates pranks and send them. A prank is composed of one message (with a subject and a body) and at least three or more emails. It implements the ISmtpMessage to have properly formed pranks for SMTP protocol. Once the prank is created, the PrankGenerator sends it with his SmtpClient class instance.

Finally, in the smtp package, the SmtpClient class establishes a connection with the STMP server (the one specified in config.properties file) and sends the SmtpMessage prank previously created using the SMTP protocol communication ([RFC 821](https://tools.ietf.org/html/rfc821 "RFC 821")).
