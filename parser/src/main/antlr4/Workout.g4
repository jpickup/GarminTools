/*
WORKOUT GRAMMAR
Examples:
    1 mi                        - 1 mile with no pace target
    2 mi@STEADY                 - 2 miles as a named pace target
    3 km < 5:00/km              - 3km faster than 5 minutes per km
    8 km @ 5:00 - 7:00 / km     - 8km at a pace target defined as a range
    1mi @ Z3                    - 1mi at heart rate Zone 3
    2km @ 120-150bpm            - 2km at a heart rate between 120 and 150 beats per minute
    (1 mi@FAST + 400m@EASY) * 8 - interval session of 8 repeats of 1 mile fast with 400m recoveries
    1mi|Recovery                - 1mi marked with intensity recovry
*/

grammar Workout;

@header {
import java.util.ArrayList;
import com.johnpickup.garmin.common.unit.*;
}

workout returns [Workout w]
   : stepList                                   {$w = new Workout($stepList.steps);}
   ;

stepList returns [List<Step> steps]
   : s0=step                                    {$steps = new ArrayList<>(); $steps.add($s0.value);}
     ('+' s=stepList                            {$steps.addAll($s.steps);})?
   ;
    
step returns [Step value]
   : distance_step                              {$value = $distance_step.value;}
   | distance_intensity_step                    {$value = $distance_intensity_step.value;}
   | distance_pace_step                         {$value = $distance_pace_step.value;}
   | distance_pace_intensity_step               {$value = $distance_pace_intensity_step.value;}
   | distance_hr_step                           {$value = $distance_hr_step.value;}
   | distance_hr_intensity_step                 {$value = $distance_hr_intensity_step.value;}
   | distance_power_step                        {$value = $distance_power_step.value;}
   | distance_power_intensity_step              {$value = $distance_power_intensity_step.value;}
   | time_step                                  {$value = $time_step.value;}
   | time_intensity_step                        {$value = $time_intensity_step.value;}
   | time_pace_step                             {$value = $time_pace_step.value;}
   | time_pace_intensity_step                   {$value = $time_pace_intensity_step.value;}
   | time_hr_step                               {$value = $time_hr_step.value;}
   | time_hr_intensity_step                     {$value = $time_hr_intensity_step.value;}
   | time_power_step                            {$value = $time_power_step.value;}
   | time_power_intensity_step                  {$value = $time_power_intensity_step.value;}
   | open_step                                  {$value = $open_step.value;}
   | open_intensity_step                        {$value = $open_intensity_step.value;}
   | open_pace_step                             {$value = $open_pace_step.value;}
   | open_pace_intensity_step                   {$value = $open_pace_intensity_step.value;}
   | open_hr_step                               {$value = $open_hr_step.value;}
   | open_hr_intensity_step                     {$value = $open_hr_intensity_step.value;}
   | open_power_step                            {$value = $open_power_step.value;}
   | open_power_intensity_step                  {$value = $open_power_intensity_step.value;}
   | repeating_steps                            {$value = $repeating_steps.value;}
   ;
   
distance_step returns [DistanceStep value]
   : distance                                   {$value = new DistanceStep($distance.value);}
   ;

distance_intensity_step returns [DistanceStep value]
   : distance PIPE intensity                    {$value = new DistanceStep($intensity.value, $distance.value);}
   ;

distance_pace_step returns [DistancePaceStep value]
   : distance '<' pace                          {$value = new DistancePaceStep($distance.value, new MaximumPace($pace.value));}
   | distance '>' pace                          {$value = new DistancePaceStep($distance.value, new MinimumPace($pace.value));}
   | distance '@' pace_range                    {$value = new DistancePaceStep($distance.value, $pace_range.value);}
   ;

distance_pace_intensity_step returns [DistancePaceStep value]
   : distance '<' pace PIPE intensity           {$value = new DistancePaceStep($intensity.value, $distance.value, new MaximumPace($pace.value));}
   | distance '>' pace PIPE intensity           {$value = new DistancePaceStep($intensity.value, $distance.value, new MinimumPace($pace.value));}
   | distance '@' pace_range PIPE intensity     {$value = new DistancePaceStep($intensity.value, $distance.value, $pace_range.value);}
   ;

distance_hr_step returns [DistanceHeartRateStep value]
   : distance '@' hr_range                      {$value = new DistanceHeartRateStep($distance.value, $hr_range.value);}
   | distance '@' hr_zone                       {$value = new DistanceHeartRateStep($distance.value, $hr_zone.value);}
   ;

distance_hr_intensity_step returns [DistanceHeartRateStep value]
   : distance '@' hr_range PIPE intensity       {$value = new DistanceHeartRateStep($intensity.value, $distance.value, $hr_range.value);}
   | distance '@' hr_zone PIPE intensity        {$value = new DistanceHeartRateStep($intensity.value, $distance.value, $hr_zone.value);}
   ;

distance_power_step returns [DistancePowerStep value]
   : distance '@' power_range                      {$value = new DistancePowerStep($distance.value, $power_range.value);}
   | distance '@' power_zone                       {$value = new DistancePowerStep($distance.value, $power_zone.value);}
   ;

distance_power_intensity_step returns [DistancePowerStep value]
   : distance '@' power_range PIPE intensity       {$value = new DistancePowerStep($intensity.value, $distance.value, $power_range.value);}
   | distance '@' power_zone PIPE intensity        {$value = new DistancePowerStep($intensity.value, $distance.value, $power_zone.value);}
   ;

time_step returns [TimeStep value]
   : time                                       {$value = new TimeStep($time.value);}
   ;

time_intensity_step returns [TimeStep value]
   : time PIPE intensity                        {$value = new TimeStep($intensity.value, $time.value);}
   ;

time_pace_step returns [TimePaceStep value]
   : time '<' pace                              {$value = new TimePaceStep($time.value, new MaximumPace($pace.value));}
   | time '>' pace                              {$value = new TimePaceStep($time.value, new MinimumPace($pace.value));}
   | time '@' pace_range                        {$value = new TimePaceStep($time.value, $pace_range.value);}
   ;

time_pace_intensity_step returns [TimePaceStep value]
   : time '<' pace PIPE intensity               {$value = new TimePaceStep($intensity.value, $time.value, new MaximumPace($pace.value));}
   | time '>' pace PIPE intensity               {$value = new TimePaceStep($intensity.value, $time.value, new MinimumPace($pace.value));}
   | time '@' pace_range PIPE intensity         {$value = new TimePaceStep($intensity.value, $time.value, $pace_range.value);}
   ;

time_hr_step returns [TimeHeartRateStep value]
   : time '@' hr_range                          {$value = new TimeHeartRateStep($time.value, $hr_range.value);}
   | time '@' hr_zone                           {$value = new TimeHeartRateStep($time.value, $hr_zone.value);}
   ;

time_hr_intensity_step returns [TimeHeartRateStep value]
   : time '@' hr_range PIPE intensity           {$value = new TimeHeartRateStep($intensity.value, $time.value, $hr_range.value);}
   | time '@' hr_zone PIPE intensity            {$value = new TimeHeartRateStep($intensity.value, $time.value, $hr_zone.value);}
   ;

time_power_step returns [TimePowerStep value]
   : time '@' power_range                          {$value = new TimePowerStep($time.value, $power_range.value);}
   | time '@' power_zone                           {$value = new TimePowerStep($time.value, $power_zone.value);}
   ;

time_power_intensity_step returns [TimePowerStep value]
   : time '@' power_range PIPE intensity           {$value = new TimePowerStep($intensity.value, $time.value, $power_range.value);}
   | time '@' power_zone PIPE intensity            {$value = new TimePowerStep($intensity.value, $time.value, $power_zone.value);}
   ;

open_step returns [OpenStep value]
   : open                                       {$value = new OpenStep();}
   ;

open_intensity_step returns [OpenStep value]
   : open PIPE intensity                        {$value = new OpenStep($intensity.value);}
   ;

open_pace_step returns [OpenPaceStep value]
   : open '<' pace                              {$value = new OpenPaceStep(new MaximumPace($pace.value));}
   | open '>' pace                              {$value = new OpenPaceStep(new MinimumPace($pace.value));}
   | open '@' pace_range                        {$value = new OpenPaceStep($pace_range.value);}
   ;

open_pace_intensity_step returns [OpenPaceStep value]
   : open '<' pace PIPE intensity               {$value = new OpenPaceStep($intensity.value, new MaximumPace($pace.value));}
   | open '>' pace PIPE intensity               {$value = new OpenPaceStep($intensity.value, new MinimumPace($pace.value));}
   | open '@' pace_range PIPE intensity         {$value = new OpenPaceStep($intensity.value, $pace_range.value);}
   ;

open_hr_step returns [OpenHeartRateStep value]
   : open '@' hr_range                          {$value = new OpenHeartRateStep($hr_range.value);}
   | open '@' hr_zone                           {$value = new OpenHeartRateStep($hr_zone.value);}
   ;

open_hr_intensity_step returns [OpenHeartRateStep value]
   : open '@' hr_range PIPE intensity           {$value = new OpenHeartRateStep($intensity.value, $hr_range.value);}
   | open '@' hr_zone PIPE intensity            {$value = new OpenHeartRateStep($intensity.value, $hr_zone.value);}
   ;

open_power_step returns [OpenPowerStep value]
   : open '@' power_range                          {$value = new OpenPowerStep($power_range.value);}
   | open '@' power_zone                           {$value = new OpenPowerStep($power_zone.value);}
   ;

open_power_intensity_step returns [OpenPowerStep value]
   : open '@' power_range PIPE intensity           {$value = new OpenPowerStep($intensity.value, $power_range.value);}
   | open '@' power_zone PIPE intensity            {$value = new OpenPowerStep($intensity.value, $power_zone.value);}
   ;

repeating_steps returns [RepeatingSteps value]
   : '('
     s=stepList                                 {$value = new RepeatingSteps($s.steps);}
     ')'
     '*' cardinal                               {$value.setRepetitions($cardinal.value);}
   ;

distance returns [Distance value]
   : number distance_unit                       {$value = new Distance($number.value, $distance_unit.value);}
   ;
   
distance_unit returns [DistanceUnit value]
   : 'm'                                        {$value = DistanceUnit.METRE;}
   | 'mi'                                       {$value = DistanceUnit.MILE;}
   | 'km'                                       {$value = DistanceUnit.KILOMETRE;}
   ;

pace returns [PaceLimit value]
   : time '/' distance_unit                     {$value = new PaceLimit($time.value, PaceUnit.perDistanceUnit($distance_unit.value));}
   ;
   
pace_range returns [Pace value]
   : t1=time '-' t2=time '/' distance_unit      {$value = new PaceRange($t1.value, $t2.value, PaceUnit.perDistanceUnit($distance_unit.value));}
   | name                                       {$value = new PaceName($text);}
   ;

hr_range returns [HeartRate value]
   : h1=cardinal '-' h2=cardinal hr_unit        {$value = new HeartRateRange($h1.value, $h2.value, $hr_unit.value);}
   ;

hr_unit returns [HeartRateUnit value]
   : 'bpm'                                      {$value = HeartRateUnit.BPM;}
   ;

hr_zone returns [HeartRateZone value]
   : 'Z1'                                       {$value = HeartRateZone.Z1;}
   | 'HZ1'                                      {$value = HeartRateZone.Z1;}
   | 'z1'                                       {$value = HeartRateZone.Z1;}
   | 'Z2'                                       {$value = HeartRateZone.Z2;}
   | 'HZ2'                                      {$value = HeartRateZone.Z2;}
   | 'z2'                                       {$value = HeartRateZone.Z2;}
   | 'Z3'                                       {$value = HeartRateZone.Z3;}
   | 'HZ3'                                      {$value = HeartRateZone.Z3;}
   | 'z3'                                       {$value = HeartRateZone.Z3;}
   | 'Z4'                                       {$value = HeartRateZone.Z4;}
   | 'HZ4'                                      {$value = HeartRateZone.Z4;}
   | 'z4'                                       {$value = HeartRateZone.Z4;}
   | 'Z5'                                       {$value = HeartRateZone.Z5;}
   | 'HZ5'                                      {$value = HeartRateZone.Z5;}
   | 'z5'                                       {$value = HeartRateZone.Z5;}
   ;

power_range returns [Power value]
   : p1=cardinal '-' p2=cardinal power_unit     {$value = new PowerRange($p1.value, $p2.value, $power_unit.value);}
   ;

power_unit returns [PowerUnit value]
   : 'W'                                        {$value = PowerUnit.WATTS;}
   ;

power_zone returns [PowerZone value]
   : 'PZ1'                                      {$value = PowerZone.PZ1;}
   | 'PZ2'                                      {$value = PowerZone.PZ2;}
   | 'PZ3'                                      {$value = PowerZone.PZ3;}
   | 'PZ4'                                      {$value = PowerZone.PZ4;}
   | 'PZ5'                                      {$value = PowerZone.PZ5;}
   | 'PZ6'                                      {$value = PowerZone.PZ6;}
   | 'PZ7'                                      {$value = PowerZone.PZ7;}
   ;

time returns [Time value]
   : DIGIT + COLON DIGIT DIGIT                  {$value = Time.parseTime($text);}
   ;

open
   : 'Open'
   | 'open'
   | 'OPEN'
   ;

intensity returns [StepIntensity value]
  : 'ACTIVE'                                    {$value = StepIntensity.ACTIVE;}
  | 'Active'                                    {$value = StepIntensity.ACTIVE;}
  | 'active'                                    {$value = StepIntensity.ACTIVE;}
  | 'REST'                                      {$value = StepIntensity.REST;}
  | 'Rest'                                      {$value = StepIntensity.REST;}
  | 'rest'                                      {$value = StepIntensity.REST;}
  | 'WARMUP'                                    {$value = StepIntensity.WARMUP;}
  | 'Warmup'                                    {$value = StepIntensity.WARMUP;}
  | 'warmup'                                    {$value = StepIntensity.WARMUP;}
  | 'COOLDOWN'                                  {$value = StepIntensity.COOLDOWN;}
  | 'Cooldown'                                  {$value = StepIntensity.COOLDOWN;}
  | 'cooldown'                                  {$value = StepIntensity.COOLDOWN;}
  | 'RECOVERY'                                  {$value = StepIntensity.RECOVERY;}
  | 'Recovery'                                  {$value = StepIntensity.RECOVERY;}
  | 'recovery'                                  {$value = StepIntensity.RECOVERY;}
  ;

number returns [double value]
   : DIGIT + (POINT DIGIT +)?                   {$value = Double.parseDouble($text);}
   ;

cardinal returns [int value]
   : DIGIT +                                    {$value = Integer.parseInt($text);}
   ;

name
   : LETTER (LETTER | DIGIT)*
   ;

LETTER
   : ('a' .. 'z') | ('A' .. 'Z')
   ;


DIGIT
   : ('0' .. '9')
   ;


POINT
   : '.'
   ;
   
COLON
   : ':'
   ;

PIPE
   : '|'
   ;

WS
   : [ \r\n] -> skip
   ;
