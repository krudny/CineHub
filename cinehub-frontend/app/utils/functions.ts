export function convertToHours(minutes: number) {
  const hours = Math.floor(minutes / 60);
  const minutes_left= minutes % 60;
  return hours + ":" + minutes_left + "h";
}