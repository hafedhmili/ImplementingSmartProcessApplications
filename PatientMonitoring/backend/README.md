# Heart Monitoring (Smart Process) Application
### Hafedh Mili, Ghizlaine El Boussaidi & Haroun Mili, with input from Prof. Mohamed Bouguessa, and Dr. Kamel Mili, M.D.

This section of the repository presents the heart monitoring application. The case study was inspired by Dr. Kamel Mili, a practicing cardiologist with 40+ years of clinical experience based in Tunisia. The functional and architectural design were done by Hafedh Mili and Ghizlane ElBoussaidi. Prof. Mohamed Bouguessa, a machine learning expert specializing in the classification of sequential and graph data helped with the machine learning strategy. Haroun Mili implemented the machine learning component, i.e. training an ML model with "matching" published datasets, and exposing it as a (micro)service to classify incoming ECGs.

We introduce the application briefly in the [next section](#overview). The high level architecture is presented in the [High-Level Architecture](#high-level-architecture) section. Section [Heart Monitor](#heart-monitor) gives a brief introduction to the design of the heart monitor. The back-end is explained in [Back End](#back-end).

## Overview

This case study falls into the so-called *connected health* field, which Wikipedia defines as "a socio-technical model for healthcare management and delivery ... to provide healthcare services remotely". *Remote patient monitoring* enables us to combine constant monitoring with minimal healthcare visits. The medical literature reports many instances where remote patient monitoring helped discharge patients from hospitals or other institutional settings, enabling them to receive in-person care at home (see Section 4.1.1 of the book).

Our heart monitoring SPA aims at detecting and monitoring *Atrial Fibrillation*, which is one of the most common forms of irregular heart rhythm (*heart arrhythmia*). It was recommended to us by Dr. Kamel Mili, for two main *technical* reasons:

  - It can be diagnosed with electro-cardiograms (ECGs), including low-resolutions ones. The following figure shows an ECG exhibiting AFib (*irregularly irregular*, top one) compared to a normal ECG (lower one)![AFIB-ECG](../images/Afib_ECG.jpg)
  - Machine learning techniques have been used successfully to predict AFib from ECGs([Christopoulos et al., 2020], [Matias et al., 2021], [Noseworthy et al., 2022], see full references in Chapter 4).
  
  From a *medical* point of view, it is an interesting candidate for constant/remote monitoring because AFib is often asymptomatic, and if left untreated, it can lead to serious complications including stroke and heart failure (see citations in Chapter 4). Finally, in terms of *social impact*, it happens to affect 1 to 2 \% of the general population, increasing to 4 \% over the age of 65, and more than 12 \% for people over 80.
  
## High Level Architecture

The next figure shows a context diagram for our heart remote patient monitoring application.![context-diagram-heart-RPMA](../images/context-diagram-connected-health.png)
The heart monitor, per se, is illustrated by a smart watch worn by the patient, which is a plausible scenario as: 1) there are many such smart watches on the market that are able to record two-lead ECGs, and 2) two leads is all we need for classifying/recognizing AFIB.

The Figure also shows that the cloud-hosted heart RPMA back-end can communicate with health care professionals (Doctors, nurses) working in an institutional setting (clinics, hospitals), and with a care giver who can be a live-in relative of the patient, or a home/visiting nurse. *For the purposes of this case study, we will not develop functionalities for healthcare professionals or care givers*.

For the heart monitoring app, we chose to use two commercial products for the IoT and cloud tiers:

- [Azure](https://azure.microsoft.com/)
- [Azure IoT](https://azure.microsoft.com/en-us/solutions/iot)

The resulting architecture is shown in the following Figure (Figure 7.1 from section 7.2.2 of Chapter 7 of the book). ![heart-RPMA high level architecture](../images/cardiology-high-level-architecture.png)
We use IoT Hub to  ommunicate between the heart monitors and the Azure-hosted heart RPMA back-end. To be able to send telemetry data (ECGs) and receive commands from the back-end via IoT Hub, the heart monitors (devices) need to "register" with IoT Hub (stepp (1)): this is done with Azure IoT's Device Provisiong Service (DPS), which obtains from IoT hub connection endpoints and credentials (step (2)), which it communicates back to the devices (step (3)). From that point on, heart monitors will communicate with IoT hub directly (step (4)) which forwards received events to Azure Event Grid, which implements a publish and subscribe protocol to communicate events of interest to the components of the back-end. Those components include:

 - The component that uses machine learning to classify the most recently uploaded ECG.
 - The component that uses business rules to figure out what to do about the results of the classification. Some of the possible outcomes include taking more frequent, or longer ECGs.
 
The latter requires communicating with the heart monitor to provide it with new recording modalities--frequency and duration of ECG. This "cloud-to-device" communication is illustrated by steps (8) (from back-end to IoT hub) and (9) (from IoT hub to device).
## Heart monitor

We like to think of the "Heart monitor" component of our heart RPMA as consisting of two layers:
 1. The actual "physical device" that is attached to a patient and that knows how to record an ECG of a certain duration in a specified format, when "it is told",
 2. A software layer that "drives" the "physical device", by: a) setting its recording modalities, and b) acting as an intermediary with IoT Hub.

This dichotomy is typical of IoT applications where the term "device" usually refers to a microcontroller (e.g. ESP32) or a mini-computer (e.g. a RASPBERRY PI), to which are connected actual *physical sensors* that capture the data of interest, and that communicates with an IoT framework (Azure IoT in our case).

For the purposes of this case study, we are *simulating* the physical layer (not using real smart watches), but implementing the software layer the way it would be in real life. This is described in more detail in [Architecture of the device layer](../heartmonitor/README.md). 
## Back end

The application back-end executes the main steps of the heart RPMA processing loop (see below). 

![heart-RPMA processing loop](../images/heart-RPMA-processing-loop.png)

The first question that we need to answer is, "who is driving", the back-end or the heart monitor.

According to Algorithm 4 below, it is the back-end that is "driving". It beats the drum (the 'while true do' loop of step (3) and step (4)), and instructs the heart monitor to record and submit an ECG when warranted. We know that this is possible with Azure IoT, where the cloud tier can send commands to devices through IoT hub (see Section 6.3.2 of the book). However, this has the potential to creates lots of 'busy waiting' ![busy waiting](https://en.wikipedia.org/wiki/Busy_waiting), with one process instance of the processing loop for every heart monitor worn by a patient served by the back-end.

On the other hand, if we look at the high-level architecture diagram above, the components of the back-end are connected to Azure's Event Grid, which is a publish and subscribe event handler. This suggests that the heart RPMA back-end is event driven by the arrival of new ECGs. That is far more efficient. But what happens if we don't hear from the heart monitor for hours, days, or weeks on end? Should we be concerned about the patient--or just the heart monitor?

We will need a mixture of both: 1) the processing loop above, amended to add handling of failures in step (10) when the back-end fails to hear from the heart monitor, and 2) an event driven loop that reacts to incoming ECGs, and that "resets the counter". This will be discussed here [Architecture of back-end](backend/README.md).
