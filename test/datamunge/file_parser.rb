class String
  def r
    self.gsub(/"/, '\"').gsub(/[']/, '\\\\\'')
  end
end

file = File.new('charity.txt', 'r')

content = ""

file.each_line("\n") do |row|
  bleh = row.gsub(/  /, " ")
  content << bleh
end

content = content.gsub!(/\r\n?/, "");
content = content.gsub!(/  /, " ")
splitted = content.split('|')


organisations = []
org = {}
names = [:id, :name, :desc, :logo]
on = 0

splitted.each_with_index do |cell, index|
  if cell =~ /.(jpg|gif)/
    stuff = cell.split(/ /)

    org[:logo] = stuff[0].chomp
    if(org[:logo] !~ /^http/)
      org[:logo]= 'http://donationsstatic.ebay.com/extend' + org[:logo]
    end

    organisations << org

    org = {}
    org[:id] =  stuff[-1].chomp
    on = 1
  else
    org[names[on]] = cell
    on = on + 1
  end

  break if index > 2000
end

organisations.each do |org|
  puts "Organisation.new(:paypal_id => '#{org[:id].r}', :logo => '#{org[:logo].r}', :name => '#{org[:name].r}', :desc => '#{org[:desc].r}').save"
end
