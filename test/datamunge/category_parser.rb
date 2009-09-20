file = File.new('charity_category.txt', 'r')

causes = {}
causes['1'] = "Culture, Sport and Recreation"
causes['2'] = "Education and Science"
causes['3'] = "Health and Medicine"
causes['4'] = "Social Services and Relief"
causes['5'] = "Environment and Protection of Animals"
causes['6'] = "Housing and Community Affairs"
causes['7'] = "Civil Rights and Citizenship"
causes['8'] = "Philanthropic Intermediation"
causes['9'] = "International"
causes['10'] = "Business and Professional"
causes['11'] = "Religious"

#
#causes.each do |k, v|
#  puts "Causes.new(:name => '#{v}').save"
#end
#
#
#causes.each do |k, v|
#  puts "Causes.find(:first, :conditions => { :name => '#{v}' }).delete"
#end
      
logfile = File.new("log.txt", "w")

logfile << "class PopulateMappings < ActiveRecord::Migration
  def self.up\n"

logfile << "user = User.find(:first)\n\n"

file.each_line("\n") do |row|
  splitted = row.split('|')
  next if splitted.size == 1

  org_paypal = splitted[0]
  cause_name = causes[splitted[1].chomp.strip]

  logfile << "org = Organisation.find(:first, :conditions => { :paypal_id => '#{org_paypal}' })\n"
  logfile << "if not org.nil?\n"
  logfile << "  cause = Cause.find(:first, :conditions => { :name => '#{cause_name}' })\n"
  logfile << "  Mapping.new(:organisation_id => org.id, :cause_id => cause.id, :user_id => user.id).save\n"
  logfile << "  puts \"Done \" + org.name + \" for \" + cause.name \n"
  logfile << "end\n\n"
end

logfile << " end \n\n def self.down
  end
end
"