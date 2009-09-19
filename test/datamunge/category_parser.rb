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


causes.each do |k, v|
  #puts "Tag.new(:name => '#{v})'"
end

file.each_line("\n") do |row|
  splitted = row.split('|')
  next if splitted.size == 1

  org_paypal = splitted[0]
  cause_name = causes[splitted[1].chomp.strip]

  puts "org = Organisation.find(:first, :conditions => { :paypal_id => '#{org_paypal}' })"
  puts "cause = Causes.find(:first, :conditions => { :name => '#{cause_name}' })"
  puts "Mapping.new(:organisation_id => org.id, :cause_id => cause.id, :user_id = '1').save"
  break
end