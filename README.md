Yo les potos, 

Pour pouvoir coder il faut télécharger **javaFx 15 **-> https://gluonhq.com/products/javafx/

Puis quand le projet sera ouvert, il faudra ajouter dans **File>Project Structure>Librairies** le fichier lib de javaFx en mettant le chemin d'accès.

Pour Run le projet, faudra ajouter dans la configuration, dans VM options : **--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml,javafx.media**

PATH_TO_FX est à configurer dans **File>Settings>Appearance & Behavior>Path Variables**. Il faudra mettre le nom "PATH_TO_FX" et les chemin d'accès du fichier lib de JavaFx.

Voili Voilou