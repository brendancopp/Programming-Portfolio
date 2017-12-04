import sys
import path_config as cfg
from County import ca_los_angeles, in_miami, run_county


#Set path variables using config
input_path = cfg.input_path
output_path = cfg.output_path

#Run county
run_county(ca_los_angeles, (input_path, output_path))
run_county(in_miami, (input_path, output_path))