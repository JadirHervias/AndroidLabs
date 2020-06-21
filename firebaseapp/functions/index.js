const functions = require("firebase-functions");

const admin = require("firebase-admin");
admin.initializeApp(functions.config().firebase);
/**
 * Se lanza cuando se 'onCreate' un nuevo nodo para la referencia de '/posts/{postid}'
 * https://firebase.google.com/docs/functions/database-event
 */
exports.sendNotification = functions.database
  .ref("/posts/{postid}")
  .onCreate((snapshot) => {
    // Obtenemos el post recientemente creado en la Realtime Database
    const post = snapshot.val();
    console.log("Post created:", post);

    // Recuperamos los datos del usuario relacionado al post de feirebase
    return admin
      .database()
      .ref("/users/" + post.userid)
      .once("value")
      .then((snapshot) => {
        const user = snapshot.val();
        console.log("User:", user);
        // Detalles de la notificacion
        const payload = {
          notification: {
            title: "¡Nuevo post publicado!",
            body: `${user.displayName} acaba de publicar "${post.title}".`,
            icon: "ic_picture",
            sound: "default",
            // clickAction: '.activities.MainActivity',
            // badge: '1'
          },
          data: {
            extra: "extra_data",
          },
        };
        // Configura la prioridad, tiempo de vida y criterio de agrupamiento
        const options = {
          priority: "high",
          timeToLive: 60 * 60 * 24,
          collapseKey: "posts",
          contentAvailable: true,
        };
        // Enviar la notificacion a los dispositivos suscritos al topico ALL
        const topic = "ALL"; // (ver MyFirebaseInstanceIDService.java)
        return admin
          .messaging()
          .sendToTopic(topic, payload, options)
          .then((response) => {
            console.log("Successfully sent message:", response);
          });
      });
  });
