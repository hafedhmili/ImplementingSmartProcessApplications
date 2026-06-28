# The Heart Monitor Component

## Overview
As explained in [Architecture of the device layer](../README.md), we think of the "Heart monitor" component of our heart RPMA as consisting of two layers:
 1. The actual "physical device" that is attached to a patient and that knows how to record an ECG of a certain duration in a specified format, when "it is told",
 2. A software layer that "drives" the "physical device", by: a) setting its recording modalities, and b) acting as an intermediary with IoT Hub.

We are *simulating* the physical layer (not using real smart watches) with the  heartmonitoringdevice project described in the section [The heartmonitoringdvice project](#The-heartmonitoringdvice-project). 

We are implementing the software layer the way it would be in real life, using Azure IoT Java SDK. This is described in more detail in [The hearmonitoringdevicedriver project](#The-hearmonitoringdevicedriver-project). 

## The heartmonitoringdevice project

## The hearmonitoringdevicedriver project

