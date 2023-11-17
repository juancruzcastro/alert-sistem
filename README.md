# alert-sistem
Es un sistema que envía alertas a los usuarios sobre distintos temas. El mismo permite registrar usuarios, registrar temas y enviar alertas, entre otras cosas.
1. Se pueden registrar usuarios que recibirán alertas. 
2. Se pueden registrar temas sobre los cuales se enviarán alertas.
3. Los usuarios pueden optar sobre cuales temas quieren recibir alertas.
4. Se puede enviar una alerta sobre un tema y lo reciben todos los usuarios que han optado recibir alertas de ese tema.
5. Se puede enviar una alerta sobre un tema a un usuario específico, solo lo recibe ese único usuario.
6. Una alerta puede tener una fecha y hora de expiración. 
7. Hay dos tipos de alertas: Informativas y Urgentes.
8. Un usuario puede marcar una alerta como leída.
9. Se pueden obtener todas las alertas no expiradas de un usuario que aún no ha leído. 
10. Se pueden obtener todas las alertas no expiradas para un tema. Se informa para cada alerta si es para todos los usuarios o para uno específico.
Tanto para el punto 9 como el 10, el ordenamiento de las alertas es el siguiente: las Urgentes van al inicio, siendo la última en llegar la primera en obtenerse (LIFO).
Y a continuación las informativas, siendo la primera en llegar la primera en obtenerse (FIFO).
Ej: Dadas las siguientes alertas Informativas y Urgentes que llegan en el siguiente orden: I1,I2,U1,I3,U2,I4 se ordenarán de la siguiente forma --> U2,U1,I1,I2,I3,I4.

Se agregó algunos test unitarios. El sistema valida que:
1. Se registren usuarios correctamente.
2. Se registren temas correctamente.
3. Un usuario pueda agregar temas de interes. Si el usuario no existe arroja error.
4. Se envien alertas correctamente. Si el tema o el usuario no existe arroja error.
