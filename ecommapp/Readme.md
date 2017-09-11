
<b>Ecommapp application demonstrates implementation of CQRS pattern using Axon framework </b>

Ecommapp has two applications, ecommapp-shoppingcart, and ecommapp-orderprocess. These two apps communicate through Kafka messaging system.
You need to install Kafka first in order to work these apps. MongoDB is used as Eventstore in both applications. MongoDB and kafka properties can be changed from application.properties file.

Follow the tutorial given below on installing Kakfa and zookeeper.

https://www.tutorialspoint.com/apache_kafka/apache_kafka_installation_steps.htm

The ecommapp-shoppingcart app handles commands that update the shopping cart. You will find a REST API in the ecommapp-shoppingcart for sending the commands to the app.

Following are the commands handled by ecommapp-shoppingcart

1. CreateCartCommand - Creates a cart entry in the db for given customer with Cart Id and Customer Id.
2. AddItemCommand - Add given item to the cart.
3. RemoveItemCommand - Removes given item from the cart.
4. PlaceOrderCommnand - This raises an OrderPlacedEvent for the ecommapp-orderprocess for processing order.

OrderPlacedEvent starts a saga in the ecommapp-orderprocess application that in turn fires new commands for payment and shipment.
On receiving PaymentDoneEvent and ShipmentDeliveredEvent the saga ends.

ecommapp-orderprocess has REST API to send CompleteShipmentCommand.

<b>Axon integration with Kafka</b>
<br>Axon is integrated with kafka through spring-integration API. Axons ChannelAdaptes are used to hook with spring message channels.
OutboundEventChannelAdapter in ecommapp-shoppingcart app publishes events on spring output message channels and InboundEventChannleAdapter  in
ecommapp-orderprocess subscribes to the spring input channel for events which in turn invokes event handlers.
