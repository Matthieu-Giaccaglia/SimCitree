# SimCitree

![SimCitree Logo](/documents/logo_simcitree.png)


SimCitree est un simulateur de forêt très basique.

Date : Octobre 2020 à début Janvier 2021.

## Projects Status

Le projet est terminé.

## Contexte

Lors du 3ème semestre à l'IUT Informatique (de Montpellier), en groupe de 4, nous avons dû réaliser un simulateur de forêt assez simpliste.

Notre tuteur de projet avait déjà créé un simulateur semblabe en Python, mais il voulait que nous le recréions en l'optimisant. Ainsi est né SimCitree ! (jeu de mot avec Simcity et Tree).

## Documents et autres

La documentation et les diapos ont été rédigées/crées lors au même moment que le développement du programme, il est donc normal qu'elles ne soient absolument pas parfaites.

- [Les diapositives des présentations](/documents/diapos)
- [Le rapport (la documentations)](/documents/rapport_de_projet_simcitree.pdf)
- [Le .jar](/documents/SimCitree.jar)

## Installation

- [Java](https://www.java.com/fr/) 
- Java 11 (Pour le développement)
- JavaFX 11 (Pour le développement)


## Installation avec IntellJ

*Nous avions utilisé que cet IDE pour le développement*

- Dans **File>Project Structure>Librairies**, ajouter le chemin du répertoire "lib" de javaFX 11 
- Dans **File>Settings>Appearance & Behavior>Path Variables**, créer la variable PATH_TO_FX et y mettre le chemin du répertoire "lib" de javaFX.
- Dans la configuration du **RUN**, dans la section **VM options**, mettre **--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml,javafx.media**

## Licence

Copyright [2020] [Birembaut Mateusz, Collé Florian, Deneuville Walter, Giaccaglia Matthieu]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
