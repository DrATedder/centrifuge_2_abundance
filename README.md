# centrifuge_2_abundance
A simple Java-based GUI (python backend) to take a directory of centrifugeReport.txt (metagenomic analysis) and produce 'abundance' files based on user-defined thresholds.

[Centrifuge](https://github.com/DaehwanKimLab/centrifuge "Link to Centrifuge github repository") produces OTU taxonomic classifications based on read similarity. It also provides you with a number of reads which map to each OTU allowing you to calculate abundance, and make decisions about what threshold level of abundance should an OTU be considered 'meaningful' to your particular project. The centrifuge_2_abundance App allows the user to specify a directory containing cetrifugeReport.txt outputs, and specify an abundance threshold (i.e. 0.01 or 0.1 etc) to create 'abundance' files. These files will consider only 'genus' taxRank level OTUs and below (inc. 'species', 'subspecies' and 'leaf'). Output files are in two column csv format, but with a txt extension. 

## Appearance

![Screenshot](https://github.com/DrATedder/centriguge_2_abundance/blob/470ce5c1c30d0f3647da46a5d4a0df0f7f354eac/centrifuge_2_abundance.png "Screenshot of centrifuge_2_abundance GUI")

## Features

- Browse to select the input directory.
- Define an abundance threshold.
- Generate abundance based on the selected threshold.
- Option to start again or exit the application.
  
## Prerequisites

- Java Development Kit (JDK) installed.
- Python installed with the necessary dependencies.

## Usage

1. Clone or download this repository to your local machine.
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Build and run the `centrifuge_2_abundance` class to start the application.
4. Use the GUI to select the input directory and define the abundance threshold.
5. Click the "Generate Abundance" button to run the conversion.
6. Messages and output will be displayed in the application.

### Example output file ###

|  OTU   |  UniqR   | Abundance   |
| --- | --- | --- |
|Azorhizobium caulinodans | 1725 | 0.03|
|Cellulomonas gilvus | 2019 | 0.03|
|Myxococcus xanthus | 5519 | 0.08|
|Myxococcus macrosporus | 4463 | 0.07|
|Stigmatella aurantiaca | 1622 | 0.02|
|Cystobacter fuscus | 2504 | 0.04|
|Archangium gephyra | 3011 | 0.04|
|Chondromyces crocatus | 1719 | 0.03|
|Sorangium cellulosum | 16403 | 0.24|
|Vitreoscilla filiformis | 1746 | 0.03|
|Lysobacter enzymogenes | 44962 | 0.66|
|Stella humosa | 2887 | 0.04| 

**Note**. Column headers are for illustrative purposes only. Abundance files are output without headers.

**Threshold** can be any integer/float below 100, and output files are written into the directory containing input files. Please be aware that the threshold chosen isn't incorporated into the output file name, so you run the risk of overwriting previous abundance files if you run the script multiple times with different thresholds.

**Pipeline integration:** To integrate this functionality into a bash or python pipeline, please see the python script "genus_level_read_count_abundance.py". More information can be found [here](https://github.com/DrATedder/ancient_metagenomics/blob/main/README.md "Link to github repository").

## Author

- Dr. Andrew Tedder
- University of Bradford

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/DrATedder/centriguge_2_abundance/blob/470ce5c1c30d0f3647da46a5d4a0df0f7f354eac/LICENSE "Link to license information") file for details.
