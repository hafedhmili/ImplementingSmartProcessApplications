# Implementing Smart Process Applications

## Compagnon web site to the book

## Implementing Smart Processes Using IoT, Machine Learning, and Business Rules"

### by Hafedh Mili & Ghizlane Elboussaidi

### with contributions from Mounir Boukadoum, Anis Boubaker, Petko Valtchev, Irina Muhina, and Mohamed Bouguessa

This is the companion web site to the aforementioned book, where we go over the design and implementation details of the two case studies introduced in "Chapter 4 - Case Studies for the book", which consist of two applications:

- A heart monitoring application that aims at detecting _Atrial Fibrillation_, using a portable heart monitor that records low-resolution ECGs, and sends them to the SPA back-end that does the detection, and acts upon it depending on several factors, including the patient's medical history, and what they are currently doing
- A SPA that helps grocery shoppers make healthy food purchases, called _HGSA_, for _Healthy Grocery Shopping Application_. The HGSA application assesses the nutritional and health value of food items that she shopper puts in her/his cart, based on her/his health profile, dietary restrictions, and taste preferences. HGSA relies on "smart" shopping carts that scan the products put the shopper's physical cart, retrieves the nutritional value of such products from corporate or public databases, and matches those against the shopper's profile and shopping history to acquiese or suggest alternatives.

Recall that the technological ingredients of SPAs are presented in separate parts of the book. At the end of each such Part of the book, we revisit the two case studies to apply what we just learned. Specifically:

- _Chapter 7 - SPA Architecture for the Case Studies_, which concludes _Part III - Infrastructure_ revisits the two case studies by exploring the design space for _IoT frameworks_ and _cloud computing_ components of the resulting SPAs, in light of their non-functional requirements. This Github repository contains the infrastructure code, for both the IoT devices (or device emulators) and the SPA back-end.
- _Chapter 10 - Analytics and ML for the Case Studies_, which concludes _Part IV - Analytics and Machine Learning_, revisits the case studies to distinguish between those decisions that lend themselves to a _Machine Learning_ approach, from thoese that lend themselves to a _Business Rules Approach_. We explore at a _fairly high level_ the recommended Machine Learning approach; this aspect is detailed on this github repository, where we include the ML code, and the training data, where applicable.
- _Chapter 15 - Business Rule Case Studies_, which concludes _Part V - Business Rules_, revisits the case studies to explore the business rules-specific design space for the those decisions, in the processing loop of SPAs (see Section _3.3.3 Eliciting functional requirements for SPAs_ of the book), that call for a business rules approach. This repository includes all of the software artifacts, including the domain models (Java projects), the busines rule projects, and the business rule clients.

This repository is work in progress, in more ways than one. We are racing to complete a first implementation of the two case studies while the book manuscript is in production. _Hopefully_, we will finish before the Springer production/copy eidting team does 😊.

It will also keep evolving as better ways of doing things emerge, and based on readers' feedback, which we certainly welcome.

Hafedh Mili and Ghizlane El-Boussaidi

## Acknowledgements

We wish to acknowledge the contributions of the following colleagues and friends, to both the manuscript, and to this repository:

- Our colleague Prof. Mounir Boukadoum, who likes to call himself "A Jack of many trades", and we like to add, "a Master of many", has authored _Chapter 8 - Core Machine Learning Concepts and Techniques_ and contributed many insights for the implementations shown in this repository.
- Dr. Anis Boubaker started working with us on _Part III - Infrastructure_ before bowing out for personal reasons.
- Prof. Petko Valtchev and Dr Irinia Muhina co-authored _Chapter 9 - Analytics and machine learning in industry_, based on a study of the literature--and first hand experience, for the case of Irinia.
- Our colleague Prof. Mohamed Bouguessa, who is a Machine Laarning expert, commented on various chapters of  _Part IV - Analytics and Machine Learning_ and advised us on the implementation choices for the case studies presaented in this repository.
