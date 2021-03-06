:-dynamic facto/2,ultimo_facto/1.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Metaconhecimento

facto_dispara_regras(codigo(_, p0340), [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]).
facto_dispara_regras(sintoma(_, long_engine_cranking_time), [1]).
facto_dispara_regras(sintoma(_, sharp_acceleration), [2]).
facto_dispara_regras(sintoma(_, engine_starts_without_problems), [3]).
facto_dispara_regras(sintoma(_, hard_shifting), [4]).
facto_dispara_regras(sintoma(_, engine_light_is_on), [5, 7]).
facto_dispara_regras(sintoma(_, engine_in_rough_idle), [6, 8]).
facto_dispara_regras(sintoma(_, hard_engine_starting_condition), [9]).
facto_dispara_regras(sintoma(_, engine_dies_suddenly), [10]).
facto_dispara_regras(sintoma(_, loss_power_in_engine), [11]).
facto_dispara_regras(codiagnostico(_, p0341_p0342_p0343), [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]).
facto_dispara_regras(codiagnostico(_, p0344), [24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51]).
facto_dispara_regras(bepon(_,_), [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]).
facto_dispara_regras(bepoff(_,_), [12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]).
facto_dispara_regras(measurementV(_,_), [14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51]).
facto_dispara_regras(corrosion(_,_), [16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51]).
facto_dispara_regras(shortcircuit(_,_), [18, 19, 20, 21, 22, 23]).
facto_dispara_regras(cable(_,_), [20, 21, 22, 23]).
facto_dispara_regras(signalwire(_,_), [26, 27, 32, 33, 34, 35, 36, 37, 38, 39, 44, 45, 46, 47, 48, 49, 50, 51]).
facto_dispara_regras(ground(_,_), [26, 27, 36, 37, 38, 39, 48, 49, 50, 51]).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

ultimo_facto(9).
%ultima_regra(51).


regra 1
	se [codigo(C, p0340) e sintoma(C, long_engine_cranking_time)]
	entao [cria_facto(codiagnostico(C, p0341_p0342_p0343))].

regra 2
	se [codigo(C, p0340) e sintoma(C, sharp_acceleration)]
	entao [cria_facto(codiagnostico(C, p0341_p0342_p0343))].

regra 3
	se [codigo(C, p0340) e sintoma(C, engine_starts_without_problems)]
	entao [cria_facto(codiagnostico(C, p0341_p0342_p0343))].

regra 4
	se [codigo(C, p0340) e sintoma(C, hard_shifting)]
	entao [cria_facto(codiagnostico(C, p0341_p0342_p0343))].

regra 5
	se [codigo(C, p0340) e sintoma(C, engine_light_is_on)]
	entao [cria_facto(codiagnostico(C, p0341_p0342_p0343))].

regra 6
	se [codigo(C, p0340) e sintoma(C, engine_in_rough_idle)]
	entao [cria_facto(codiagnostico(C, p0341_p0342_p0343))].

regra 7
	se [codigo(C, p0340) e sintoma(C, engine_light_is_on)]
	entao [cria_facto(codiagnostico(C, p0344))].

regra 8
	se [codigo(C, p0340) e sintoma(C, engine_in_rough_idle)]
	entao [cria_facto(codiagnostico(C, p0344))].

regra 9
	se [codigo(C, p0340) e sintoma(C, hard_engine_starting_condition)]
	entao [cria_facto(codiagnostico(C, p0344))].

regra 10
	se [codigo(C, p0340) e sintoma(C, engine_dies_suddenly)]
	entao [cria_facto(codiagnostico(C, p0344))].

regra 11
	se [codigo(C, p0340) e sintoma(C, loss_power_in_engine)]
	entao [cria_facto(codiagnostico(C, p0344))].

regra 12
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,<,12)) e diagnostico(bepon(C,<,14))]
	entao [cria_facto(problema(C, battery_problem))].

regra 13
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,<,12)) e diagnostico(bepon(C,<,14))]
	entao [cria_facto(solucao(C, charge_or_replace_battery))].

regra 14
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,==,5))]
	entao [cria_facto(problema(C, sensor_has_a_problem))].

regra 15
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,==,5))]
	entao [cria_facto(solucao(C, replace_CMP_sensor))].

regra 16
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, yes)]
	entao [cria_facto(problema(C, sensor_has_a_problem))].

regra 17
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, yes)]
	entao [cria_facto(solucao(C, clean_sensor_with_electric_cleaner))].

regra 18
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, no) e shortcircuit(C, yes)]
	entao [cria_facto(problema(C, sensor_has_problem))].

regra 19
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, no) e shortcircuit(C, yes)]
	entao [cria_facto(solucao(C, replace_CMP_sensor))].

regra 20
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, no) e shortcircuit(C, no) e cable(C, yes)]
	entao [cria_facto(problema(C, faulty_wires_between_camshaft_position_sensor_and_ECU))].

regra 21
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, no) e shortcircuit(C, no) e cable(C, yes)]
	entao [cria_facto(solucao(C, replace_wires_between_the_sensor_and_the_ECU))].

regra 22
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, no) e shortcircuit(C, no) e cable(C, no)]
	entao [cria_facto(problema(C, control_unit_Faulty))].

regra 23
	se [codiagnostico(C, p0341_p0342_p0343) e diagnostico(bepoff(C,>=,12)) e diagnostico(bepon(C,>=,14)) e diagnostico(measurementV(C,\==,5)) e corrosion(C, no) e shortcircuit(C, no) e cable(C, no)]
	entao [cria_facto(solucao(C, replace_unit))].

regra 24
	se [codiagnostico(C, p0344) e corrosion(C, yes)]
	entao [cria_facto(problema(C, sensor_has_problem))].

regra 25
	se [codiagnostico(C, p0344) e corrosion(C, yes)]
	entao [cria_facto(solucao(C, clean_sensor_with_electric_cleaner))].

regra 26
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C, 5)) e diagnostico(signalwire(C, 5)) e ground(C, yes)]
	entao [cria_facto(problema(C, camshaft_position_sensor_damaged))].

regra 27
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C, 5)) e diagnostico(signalwire(C, 5)) e ground(C, yes)]
	entao [cria_facto(solucao(C, substitute_camshaft_position))].

regra 28
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(problema(C, incomplete_wiring_camshaft_position))].

regra 29
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(problema(C, ecu_Error))].

regra 30
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(problema(C, ecu_not_plugged))].

regra 31
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(problema(C, low_battery_voltage))].

regra 32
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(problema(C, incomplete_wiring_camshaft_position))].

regra 33
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(problema(C, ecu_Error))].
	
regra 34
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(problema(C, ecu_not_plugged))].

regra 35
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(problema(C, low_battery_voltage))].

regra 36
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(problema(C, incomplete_wiring_camshaft_position))].

regra 37
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(problema(C, ecu_Error))].
	
regra 38
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(problema(C, ecu_not_plugged))].

regra 39
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(problema(C, low_battery_voltage))].

regra 40
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(solucao(C, replace_or_add_wires_between_the_sensor_to_ECU))].

regra 41
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(solucao(C, replace_ECU_or_flash_system))].

regra 42
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(solucao(C, plug_ECU))].

regra 43
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,\==,5))]
	entao [cria_facto(solucao(C, charge_or_replace_car_battery))].

regra 44
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(solucao(C, replace_or_add_wires_between_the_sensor_to_ECU))].

regra 45
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(solucao(C, replace_ECU_or_flash_system))].

regra 46
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(solucao(C, plug_ECU))].

regra 47
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,\==,5))]
	entao [cria_facto(solucao(C, charge_or_replace_car_battery))].

regra 48
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(solucao(C, replace_or_add_wires_between_the_sensor_to_ECU))].

regra 49
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(solucao(C, replace_ECU_or_flash_system))].

regra 50
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(solucao(C, plug_ECU))].

regra 51
	se [codiagnostico(C, p0344) e corrosion(C, no) e diagnostico(measurementV(C,==,5)) e diagnostico(signalwire(C,==,5)) e ground(C, no)]
	entao [cria_facto(solucao(C, charge_or_replace_car_battery))].




%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Factos

facto(1,codigo(meu_diagnostico, p0340)).
facto(2,sintoma(meu_diagnostico, long_engine_cranking_time)).
facto(3,sintoma(meu_diagnostico, hard_shifting)).
facto(4,bepoff(meu_diagnostico, 12)).
facto(5,bepon(meu_diagnostico, 14)).
facto(6,measurementV(meu_diagnostico, 7)).
facto(7,corrosion(meu_diagnostico, no)).
facto(8,shortcircuit(meu_diagnostico, no)).
facto(9,cable(meu_diagnostico, yes)).

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
