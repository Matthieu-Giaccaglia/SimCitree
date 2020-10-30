Yo les amis, 

Pour pouvoir coder il faut télécharger **JavaFx 15** -> https://gluonhq.com/products/javafx/
Vous pouvez mettre le JDK où vous le souhaitez.

Puis, une fois que le projet sera cloné et ouvert, il faudra ajouter dans **File>Project Structure>Librairies** le fichier /lib de JavaFx 15 en mettant son chemin d'accès.

Pour **Run** le projet, faudra ajouter dans la configuration du Run, dans la section **VM options** : **--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml,javafx.media**

*PATH_TO_FX* est à configurer dans **File>Settings>Appearance & Behavior>Path Variables**. Il faudra mettre le nom "PATH_TO_FX" et les chemin d'accès du fichier /lib de JavaFx 15.

Voili Voilou